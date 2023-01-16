package hello.springtransactions.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
public class BasicTxTest {

    @Autowired
    PlatformTransactionManager txManager;

    @TestConfiguration
    static class Config {
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

    @Test
    void commit() {
        log.info("트랜젝션 시작");
        final TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜젝션 커밋 시작");
        txManager.commit(status);
        log.info("트랜젝션 커밋 완료");
    }

    @Test
    void rollback() {
        log.info("트랜젝션 시작");
        final TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜젝션 롤백 시작");
        txManager.rollback(status);
        log.info("트랜젝션 롤백 완료");
    }

    @Test
    void double_commit() {
        log.info("트랜젝션1 시작");
        final TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜젝션1 커밋");
        txManager.commit(tx1);


        log.info("트랜젝션2 시작");
        final TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜젝션2 커밋");
        txManager.commit(tx2);
    }

    @Test
    void double_commit_rollback() {
        log.info("트랜젝션1 시작");
        final TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜젝션1 커밋");
        txManager.commit(tx1);


        log.info("트랜젝션2 시작");
        final TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜젝션2 롤백");
        txManager.rollback(tx2);
    }

    @Test
    void inner_commit() {
        log.info("외부 트랜젝션 시작");
        final TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 트랜젝션 시작");
        final TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());
        log.info("내부 트랜젝션 커밋");
        txManager.commit(inner);

        log.info("외부 트랜젝션 커밋");
        txManager.commit(outer);
    }

    /**
     * 논리 트랜젝션이 하나라도 롤백되면 물리 트랜젝션은 롤백된다.
     * 내부 논리 트랜젝션이 롤백되면 롤백 전용 마크를 표시한다.
     * 외부 트랜젝션을 커밋할 때 롤백 전용 마크를 확인. 롤백 전용 마크가 표시되어 있으면 물리 트랜젝션을 롤백하고, UnexpectedRollbackException 예외를 던진다.
     */
    @Test
    @DisplayName("외부 트랜젝션이 롤백되면, 내부 트랜젝션이 commit 되어도 물리트랜젝션은 롤백 된다.")
    void outer_rollback() {
        log.info("외부 트랜젝션 시작");
        final TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜젝션 시작");
        final TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("내부 트랜젝션 커밋");
        txManager.commit(inner);

        log.info("외부 트랜젝션 롤백");
        txManager.rollback(outer);
    }

    @Test
    @DisplayName("내부 트랜젝션이 롤백되면 트랜젝션에 rollback-only 를 마킹한다. 따라서 외부 트랜젝션도 롤백전용으로 사용됨으로 롤백된다.")
    void inner_rollback() {
        log.info("외부 트랜젝션 시작");
        final TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜젝션 시작");
        final TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("내부 트랜젝션 롤백");
        txManager.rollback(inner); // rollback-only 마킹 (기존 트랜젝션을 롤백 전용으로 표시한다.)

        log.info("외부 트랜젝션 커밋");
        assertThatThrownBy(() -> txManager.commit(outer))
                .isInstanceOf(UnexpectedRollbackException.class);
    }

    /**
     * 트랜젝션 전파 옵션
     */
    @Test
    void inner_rollback_requires_new() {
        log.info("외부 트랜젝션 시작");
        final TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 트랜젝션 시작");
        final DefaultTransactionAttribute definition = new DefaultTransactionAttribute();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 항상 새로운 트랜젝션을 만든다.
        // Suspending current transaction, creating new transaction with name (기존 트랜젝션을 잠시 suspending 시키고 새로운 트랜젝션을 만든다.)
        final TransactionStatus inner = txManager.getTransaction(definition);
        log.info("inner.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 트랜젝션 롤백");
        txManager.rollback(inner);

        log.info("외부 트랜젝션 커밋");
        txManager.commit(outer);
    }
}

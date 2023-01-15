package hello.springtransactions.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

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
}

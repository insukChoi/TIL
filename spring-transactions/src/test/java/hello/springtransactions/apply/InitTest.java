package hello.springtransactions.apply;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
public class InitTest {

    @Autowired
    Hello hello;

    @Test
    void go() {
        // 초기화 코드는 스프링이 초기화 시점에 호출한다.
    }

    @TestConfiguration
    static class InitTxConfig {
        @Bean
        Hello hello() {
            return new Hello();
        }
    }

    @Slf4j
    static class Hello {

        /*
         * 초기화 코드에 Transactional 을 함께 사용하면 트랜젝션이 적용되지 않는다.
         * 왜냐하면 초기화 코드가 먼저 호출되고 그다음 AOP 가 적용되기 때문에
         */
        @PostConstruct
        @Transactional
        public void initV1() {
            final boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init @PostConstruct tx active={}", isActive);
        }

        /*
         * 대안은 스프링이 다 뜨고나서 실행되야 한다.
         * ex) ApplicationReadEvent Listener : 스프링이 다 뜨고 나서 실행
         */
        @EventListener(ApplicationReadyEvent.class)
        @Transactional
        public void initV2() {
            final boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init ApplicationReadyEvent tx active={}", isActive);
        }
    }
}

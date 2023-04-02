package com.mongo.stream.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import reactor.test.StepVerifier
import java.math.BigDecimal


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class TornadoCollectorRepositoryTest(
    private val tornadoCollectorRepository: TornadoCollectorRepository
) {

    @BeforeEach
    fun deleteAll() {
        tornadoCollectorRepository.deleteAll().block()
    }

    @Test
    fun givenValue_whenFindAllByValue_thenFindCollector() {
        tornadoCollectorRepository.save(
            givenCollectorMock()
        ).block()

        val collectorFlux = tornadoCollectorRepository.findAllByAid(AID)
        StepVerifier
            .create(collectorFlux)
            .assertNext {
                assertThat(collectorFlux.blockLast()?.aid).isEqualTo(AID)
            }
            .expectComplete()
            .verify()
    }

    private fun givenCollectorMock() =
        TornadoCollector(
            channelType = "AMD",
            aid = AID,
            PaymentInfo(
                paymentMethodType = "MONEY",
                paymentActionType = "PAYMENT",
                currency = "KRW",
                totalAmount = BigDecimal.TEN
            ),
            SettlementInfo(
                pgFeeAmount = BigDecimal.valueOf(0.1)
            )
        )

    companion object {
        private const val AID = "aid222"
    }
}
package com.mongo.stream.config

import com.mongo.stream.domain.TornadoCollector
import com.mongodb.client.model.changestream.OperationType
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.stereotype.Component


@Component
class MongoDbChangeStreamConfig(
    private val reactiveMongoTemplate: ReactiveMongoTemplate
) {
    @EventListener
    fun listenForCollectionChangeStream(event: ApplicationReadyEvent) {
        reactiveMongoTemplate.changeStream(TornadoCollector::class.java)
            .filter(where("operationType").`in`("insert", "update"))
            .listen()
            .subscribe {
                when(it.operationType) {
                    OperationType.INSERT -> println("insert = $it")
                    // insert = ChangeStreamEvent {raw=ChangeStreamDocument{ operationType=insert, resumeToken={"_data": "8264297EBB000000012B022C0100296E5A100467719689C3AB43B1A1CA0E0E88C2C38646645F6964006464297EBB0FB9BB07A14E9D800004"}, namespace=wtdev-local.tornado_collection, destinationNamespace=null, fullDocument=Document{{_id=64297ebb0fb9bb07a14e9d80, channel_type=TESLA, aid=2222222, payment_info=Document{{payment_method_type=MONEY, payment_action_type=PAYMENT, currency_type=KRW, total_amount=7000}}, settlement_info=Document{{pg_fee_amount=70}}}}, fullDocumentBeforeChange=null, documentKey={"_id": {"$oid": "64297ebb0fb9bb07a14e9d80"}}, clusterTime=Timestamp{value=7217439219461914625, seconds=1680441019, inc=1}, updateDescription=null, txnNumber=null, lsid=null, wallTime=null}, targetType=class com.mongo.stream.domain.TornadoCollector}
                    OperationType.UPDATE -> println("update = $it")
                    // update = ChangeStreamEvent {raw=ChangeStreamDocument{ operationType=update, resumeToken={"_data": "8264297ECD000000042B022C0100296E5A100467719689C3AB43B1A1CA0E0E88C2C38646645F6964006464297EBB0FB9BB07A14E9D800004"}, namespace=wtdev-local.tornado_collection, destinationNamespace=null, fullDocument=Document{{_id=64297ebb0fb9bb07a14e9d80, channel_type=TESLA, aid=2222222, payment_info=Document{{payment_method_type=MONEY, payment_action_type=CANCELED, currency_type=KRW, total_amount=7000}}, settlement_info=Document{{pg_fee_amount=70}}}}, fullDocumentBeforeChange=null, documentKey={"_id": {"$oid": "64297ebb0fb9bb07a14e9d80"}}, clusterTime=Timestamp{value=7217439296771325956, seconds=1680441037, inc=4}, updateDescription=UpdateDescription{removedFields=[], updatedFields={"payment_info.payment_action_type": "CANCELED"}, truncatedArrays=[], disambiguatedPaths=null}, txnNumber=null, lsid=null, wallTime=null}, targetType=class com.mongo.stream.domain.TornadoCollector}
                    else -> {}
                }
            }
    }
}
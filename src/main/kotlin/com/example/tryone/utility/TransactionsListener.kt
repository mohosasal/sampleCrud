package com.example.tryone.utility

import com.example.tryone.model.transaction.Transaction
import com.example.tryone.service.TransactionService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
@Component
class TransactionsListener(
    val transactionService: TransactionService,
    val objectMapper: ObjectMapper
) {
    @KafkaListener(
        topics = ["transaction"],
        groupId = "groupId"
    )
    fun transactionCreate(record: ConsumerRecord<String, String>){
        transactionService.transactionCreate(
            objectMapper.readValue(record.value(), Transaction::class.java)
        )
    }
}
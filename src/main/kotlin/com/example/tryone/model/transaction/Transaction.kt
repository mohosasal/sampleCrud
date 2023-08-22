package com.example.tryone.model.transaction

import jakarta.persistence.*
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.Setting


@Document(indexName = "transactions")
@Setting(settingPath = "static/es-settings.json")
class Transaction {
    @Field
    var walletId: Long? = null
    @Field
    var transactionType: TransactionType? = null
    @Field
    var amount: Long? = null
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO,
        generator = "transaction_id_sequence"
    )
    var id: Long? = null

    companion object {
        fun requestValidate(transaction: Transaction): Pair<Boolean, ArrayList<String>> {
            val messages: ArrayList<String> = ArrayList()
            if (transaction.walletId == null) messages.add("wallet id is null")
            if (transaction.amount == null) messages.add("wallet amount is null")
            if (transaction.amount!! < 0) messages.add("transaction amount is less than 0")
            if (transaction.transactionType == null) messages.add("transaction type is null")

            return if (messages.isEmpty()) Pair(true, messages)
            else Pair(false, messages)

        }
    }

}
package com.example.tryone.service

import com.example.tryone.exceptions.NotEnoughWalletBalanceException
import com.example.tryone.exceptions.WalletNotFoundException
import com.example.tryone.model.transaction.Transaction
import com.example.tryone.model.transaction.TransactionType
import com.example.tryone.model.wallet.Wallet
import com.example.tryone.repository.TransactionRepository
import com.example.tryone.repository.WalletRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionService(
    var transactionRepository: TransactionRepository,
    var walletRepository: WalletRepository,
    var objectMapper: ObjectMapper,
    val kafkaTemplate: KafkaTemplate<String, String>
) {
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    fun transactionCreateValidate(transaction: Transaction) {
            val wallet: Wallet?
            if (walletRepository.existsById(transaction.walletId!!)) {
                wallet = walletRepository.getReferenceById(transaction.walletId!!)
            } else throw WalletNotFoundException(transaction.walletId!!)
            if (transaction.transactionType == TransactionType.DECREASE && transaction.amount!! > wallet.balance) {
                throw NotEnoughWalletBalanceException(transaction.walletId!!)
            }

            kafkaTemplate.send("transaction", objectMapper.writeValueAsString(transaction))
    }
    fun transactionCreate(transaction: Transaction){
        var wallet: Wallet = walletRepository.getById(transaction.walletId!!)
        if (transaction.transactionType == TransactionType.INCREASE) {
            wallet.balance += transaction.amount!!
        } else {
            wallet.balance -= transaction.amount!!
        }
        transactionRepository.save(transaction)
        walletRepository.save(wallet)
    }
}
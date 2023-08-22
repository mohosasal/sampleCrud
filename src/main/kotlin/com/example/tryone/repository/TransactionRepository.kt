package com.example.tryone.repository

import com.example.tryone.model.transaction.Transaction
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : ElasticsearchRepository<Transaction,Long> {
}
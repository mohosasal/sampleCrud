package com.example.tryone.controller

import com.example.tryone.model.transaction.Transaction
import com.example.tryone.service.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TransactionController(
    private val transactionService: TransactionService,
) {
    @PostMapping("/transaction")
    fun crateTransaction(@RequestBody transaction: Transaction): ResponseEntity<ArrayList<String>> {

        val validatePair: Pair<Boolean, ArrayList<String>> = Transaction.requestValidate(transaction)
        if (!validatePair.first){
            return ResponseEntity<ArrayList<String>>(validatePair.second, HttpStatus.BAD_REQUEST)
        }
        else {
            transactionService.transactionCreateValidate(transaction)
            return ResponseEntity<ArrayList<String>>(validatePair.second, HttpStatus.OK)
        }
    }
}

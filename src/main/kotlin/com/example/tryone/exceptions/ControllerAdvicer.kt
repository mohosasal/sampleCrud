package com.example.tryone.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime


@ControllerAdvice
class ControllerAdvicer : ResponseEntityExceptionHandler() {


    @ExceptionHandler(WalletsNotFoundException::class)
    fun handleWalletsNotFoundException(
        ex: WalletsNotFoundException?, request: WebRequest?
    ): ResponseEntity<Any> {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = "there is no wallets"
        return ResponseEntity<Any>(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(WalletNotFoundException::class)
    fun handleWalletNotFoundException(
        ex: WalletNotFoundException?, request: WebRequest?
    ): ResponseEntity<Any> {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = "the wallet not found"
        return ResponseEntity<Any>(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(InternalServerErrorException::class)
    fun handleInternalServerErrorException(
        ex: InternalServerErrorException?, request: WebRequest?
    ): ResponseEntity<Any> {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = "internal error exception"
        return ResponseEntity<Any>(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NotEnoughWalletBalanceException::class)
    fun notEnoughWalletBalanceException(
        ex: NotEnoughWalletBalanceException?, request: WebRequest?
    ): ResponseEntity<Any> {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = "not enough wallet balance"
        return ResponseEntity<Any>(body, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}


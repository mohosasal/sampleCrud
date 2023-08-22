package com.example.tryone.controller

import com.example.tryone.model.wallet.Wallet
import com.example.tryone.model.wallet.WalletUpdateDTO
import com.example.tryone.service.WalletService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/wallet")
class WalletController(
    val walletService: WalletService
) {
    @GetMapping("")
    fun list(): ResponseEntity<List<Wallet>> {
        return ResponseEntity<List<Wallet>>(walletService.all(), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): ResponseEntity<Wallet> {
        return ResponseEntity<Wallet>(walletService.find(id), HttpStatus.OK)
    }

    @PostMapping("")
    fun create(@RequestBody wallet: Wallet): ResponseEntity<Wallet> {
        if (wallet.balance < 0 ) return ResponseEntity<Wallet>(HttpStatus.BAD_REQUEST)
        return ResponseEntity<Wallet>(walletService.create(wallet), HttpStatus.OK)

    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody walletUpdateDTO: WalletUpdateDTO): ResponseEntity<Wallet> {
        val updated = walletService.update(id, walletUpdateDTO)
        return ResponseEntity<Wallet>(updated, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        walletService.delete(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }

    @DeleteMapping("")
    fun deleteAll(): ResponseEntity<Void> {
        walletService.deleteAll()
        return ResponseEntity<Void>(HttpStatus.OK)

    }

}
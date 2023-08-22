package com.example.tryone.service

import com.example.tryone.exceptions.InternalServerErrorException
import com.example.tryone.exceptions.WalletNotFoundException
import com.example.tryone.exceptions.WalletsNotFoundException
import com.example.tryone.model.wallet.Wallet
import com.example.tryone.model.wallet.WalletUpdateDTO
import com.example.tryone.repository.WalletRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.BeanWrapper
import org.springframework.beans.BeanWrapperImpl
import org.springframework.stereotype.Service
import java.beans.PropertyDescriptor
import java.util.*

@Service
class WalletService(var  walletRepository: WalletRepository) {
    fun all(): List<Wallet> {
        try {
            val wallets: List<Wallet> = ArrayList<Wallet>(walletRepository.findAll())
            if (wallets.isEmpty()) throw WalletsNotFoundException()
            else return wallets
        } catch (e: Exception) {
            throw InternalServerErrorException()
        }
    }
    fun find(id: Long): Wallet {
        try {
            val wallet: Optional<Wallet> = walletRepository.findById(id)
            if (!wallet.isPresent) throw WalletNotFoundException(id)
            return wallet.get()
        } catch (e: Exception) {
            throw InternalServerErrorException()
        }
    }
    fun create(wallet: Wallet): Wallet {
        walletRepository.save(wallet)
        return wallet
    }
    fun update(id: Long, walletUpdateDTO: WalletUpdateDTO): Wallet {
        val walletRetrieved: Optional<Wallet> = walletRepository.findById(id);
        if (walletRetrieved.isPresent) {
            val newWallet: Wallet = walletRetrieved.get();
            BeanUtils.copyProperties(walletUpdateDTO,newWallet)
            return walletRepository.save(newWallet)
        } else throw WalletNotFoundException(id)
    }
    fun delete(id: Long) {
        try {
            if (!walletRepository.findById(id).isPresent) throw WalletNotFoundException(id)
            walletRepository.deleteById(id)
        } catch (e: java.lang.Exception) {
            throw InternalServerErrorException()
        }
    }
    fun deleteAll() {
        try {
            walletRepository.deleteAll()
        } catch (e: java.lang.Exception) {
            throw InternalServerErrorException()
        }
    }
}





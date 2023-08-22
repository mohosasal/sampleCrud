package com.example.tryone.repository

import com.example.tryone.model.wallet.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface WalletRepository : JpaRepository<Wallet,Long> {
}
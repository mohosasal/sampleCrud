package com.example.tryone.model.wallet

import jakarta.persistence.*


@Entity
@Table
class Wallet {
    @Id
    @SequenceGenerator(
        name = "wallet_sequence",
        sequenceName = "wallet_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.AUTO,
        generator = "wallet_sequence"
    )
    var id: Long? = null
    var balance: Long = 0
    var name: String? = null


}
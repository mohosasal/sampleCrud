package com.example.tryone.exceptions

class NotEnoughWalletBalanceException(id: Long) :
    RuntimeException(String.format("not enough balance in wallet with id %d", id)) {

}

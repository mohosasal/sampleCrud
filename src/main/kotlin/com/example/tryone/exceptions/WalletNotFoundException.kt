package com.example.tryone.exceptions

class WalletNotFoundException(id: Long) : RuntimeException(String.format("wallet with id %d not found", id)) {

}

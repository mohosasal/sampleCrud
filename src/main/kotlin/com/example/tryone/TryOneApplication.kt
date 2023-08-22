package com.example.tryone

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TryOneApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<TryOneApplication>(*args)
        }
    }
}
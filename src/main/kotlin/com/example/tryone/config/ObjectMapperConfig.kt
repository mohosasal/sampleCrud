package com.example.tryone.config
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class ObjectMapperConfig {
    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }

}
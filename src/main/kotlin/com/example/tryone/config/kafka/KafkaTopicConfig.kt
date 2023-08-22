package com.example.tryone.config.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicConfig {
    @Bean
    fun TransactoinToipc() : NewTopic{
        return TopicBuilder.name("transaction").build()
    }
}
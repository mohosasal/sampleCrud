package com.example.tryone.config.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
class KafkaConsumerConfig {
    @Value("{spring.kafka.bootstrap-servers} ")
    var bootstrapServers: String? = null

    fun consumerConfig(): HashMap<String, Any> {
        val props: HashMap<String, Any> = hashMapOf()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers as Any
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class
        return props;
    }
    @Bean
    fun consumerFactory() : ConsumerFactory<String, String> {
        return DefaultKafkaConsumerFactory(consumerConfig())
    }
}



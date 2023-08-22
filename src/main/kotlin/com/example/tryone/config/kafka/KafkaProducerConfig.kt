package com.example.tryone.config.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
class KafkaProducerConfig {
    @Value("{spring.kafka.bootstrap-servers}")
    var bootstrapServers: String? = null

    fun producerConfig(): HashMap<String, Any> {

        val props: HashMap<String, Any> = hashMapOf()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers as Any
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class
        return props;


    }
    @Bean
    fun producerFactory() : ProducerFactory<String,String>{
        return DefaultKafkaProducerFactory(producerConfig())
    }
    @Bean
    fun kafkaTemplate(
        producerFactory: ProducerFactory<String,String>
    ): KafkaTemplate<String,String>{
        return KafkaTemplate(producerFactory)
    }

}
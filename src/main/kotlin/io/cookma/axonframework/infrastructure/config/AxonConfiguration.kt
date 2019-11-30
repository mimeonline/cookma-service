package io.cookma.axonframework.infrastructure.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.mongodb.MongoClient
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventhandling.saga.repository.MongoSagaStore
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
class AxonConfig {

    @Value("\${mongodb.host.name}")
    val mongodbHost: String = ""

    @Value("\${mongodb.host.port}")
    val mongoDBPort: Int = 0

    @Value("\${mongodb.host.database}")
    val mongoDB: String? = null

    @Bean
    fun sagaStore(): MongoSagaStore {
        return MongoSagaStore.builder().mongoTemplate(axonMongoTemplate()).build()
    }
    private fun kotlinJacksonSerializer(objectMapper: ObjectMapper): Serializer {
        objectMapper.registerKotlinModule()
        return JacksonSerializer.builder().objectMapper(objectMapper).build()
    }

    @Primary
    @Bean
    fun serializer(objectMapper: ObjectMapper) = kotlinJacksonSerializer(objectMapper)

    @Qualifier("eventSerializer")
    @Bean
    fun eventSerializer(objectMapper: ObjectMapper) = kotlinJacksonSerializer(objectMapper)

    @Qualifier("messageSerializer")
    @Bean
    fun messageSerializer(objectMapper: ObjectMapper) = kotlinJacksonSerializer(objectMapper)
//    @Bean
//    @Qualifier("eventSerializer")
//    fun axonJsonSerializer() = JacksonSerializer.builder().objectMapper(jacksonObjectMapper()).build()

    @Bean
    fun tokenStore(): TokenStore {
        return MongoTokenStore.builder().mongoTemplate(axonMongoTemplate()).serializer(eventSerializer(jacksonObjectMapper())).build()
    }

    @Bean
    fun axonMongoTemplate() = DefaultMongoTemplate.builder().mongoDatabase(mongoClient()).build()



    @Bean
    fun mongoClient() = MongoClient(mongodbHost, mongoDBPort)
}

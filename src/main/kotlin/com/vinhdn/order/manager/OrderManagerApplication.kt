package com.vinhdn.order.manager

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaRepositories

class OrderManagerApplication

fun main(args: Array<String>) {
    runApplication<OrderManagerApplication>(*args)
}

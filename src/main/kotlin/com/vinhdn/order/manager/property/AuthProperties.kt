package com.vinhdn.order.manager.property

import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "auth")
@Configuration
class AuthProperties: InitializingBean {

    lateinit var clientId: String
    lateinit var clientSecret: String

    override fun afterPropertiesSet() {

    }
}
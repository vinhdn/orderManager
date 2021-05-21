package com.humaxdigital.demo.config

import com.humaxdigital.demo.entity.Staff
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CompanyConfig {

    @Bean("president")
    fun getPresident(): Staff {
        return Staff("Patrick")
    }

}
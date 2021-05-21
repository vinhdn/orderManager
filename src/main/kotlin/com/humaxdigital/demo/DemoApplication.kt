package com.humaxdigital.demo

import com.humaxdigital.demo.entity.Staff
import com.humaxdigital.demo.service.StaffService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaRepositories
class DemoApplication

fun main(args: Array<String>) {
    val context = runApplication<DemoApplication>(*args)
    val staffService = context.getBean(StaffService::class.java)
    val staff = staffService.getStaffByName("Seven")
    staff?.logWork()
    val president = staffService.president
    president.logWork()
}

package com.humaxdigital.demo.entity

import org.springframework.stereotype.Component

@Component
data class Staff(val name: String? = null,
                 var department: Department? = null) {

    fun logWork() {
        print("$name logged work today")
    }
}
package com.vinhdn.order.manager.repository

import com.vinhdn.order.manager.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, String> {
    fun findAllByNameContainsOrPhoneContains(name: String, phone: String)
    fun findAllByShopId(shopId: String)
}
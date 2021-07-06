package com.vinhdn.order.manager.entity

import javax.persistence.*

@Entity
@Table(name = "customer")
data class Customer(
    @Id
    val id: String = "",
    val name: String = "",
    val phone: String = "",
    val address: String = "",

    @OneToMany
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    val orders: Set<Order>? = null
)

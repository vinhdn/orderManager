package com.vinhdn.order.manager.entity

import javax.persistence.*

@Entity
@Table(name = "customer")
data class Customer(
    @Id
    var id: String = "",
    val name: String = "",
    val phone: String = "",
    val address: String = "",
    val shopId: String? = null,

    @Transient
    val orders: List<UserOrder>? = null
)

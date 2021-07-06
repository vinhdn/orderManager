package com.vinhdn.order.manager.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "order")
data class Order(
    @Id
    val id: String = "",
    @Column(name = "shop_id")
    val shopId: String = "",
    @Column(name = "customer_id")
    val customerId: String = "",
    @Column(name = "total_price")
    val totalPrice: Long = 0,
    val note: String? = null,

    @Column(name = "sales_channel")
    val salesChannel: String? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    val createDate: Date? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    val customer: Customer? = null,

    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    val shop: Shop? = null,

    @ManyToOne
    @JoinColumn(name = "user_create", insertable = false, updatable = false)
    val userCreate: User? = null,

    @ManyToMany
    @JoinColumn(name = "order_id")
    val orderProducts: Set<OrderProduct>? = null,

    @OneToMany
    @JoinColumn(name = "order_id")
    val orderShips: Set<OrderShip>? = null
)

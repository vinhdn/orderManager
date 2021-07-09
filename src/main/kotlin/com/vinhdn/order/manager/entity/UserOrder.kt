package com.vinhdn.order.manager.entity

import org.hibernate.annotations.Where
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_order")
data class UserOrder(
    @Id
    var id: String = "",
    @Column(name = "shop_id")
    var shopId: String? = "",
    @Column(name = "customer_id")
    val customerId: String = "",
    @Column(name = "total_price")
    var totalPrice: Long = 0,
    val note: String? = null,

    @Column(name = "sales_channel")
    val salesChannel: String? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    var createDate: Date? = null,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    var customer: Customer? = null,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    var shop: Shop? = null,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "user_create", insertable = false, updatable = false)
    var userCreate: User? = null,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    val orderProducts: List<OrderProduct>? = null,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    val orderShips: List<OrderShip>? = null
)

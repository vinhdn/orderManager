package com.vinhdn.order.manager.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "order_ship")
data class OrderShip(
    @Id
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    val order: Order? = null,

    @ManyToOne
    @JoinColumn(name = "ship_partner_id", insertable = false, updatable = false)
    val shipPartner: ShipPartner? = null,

    val status: Int = 0,
    val price: Long = 0,
    val note: String = "",
    @Temporal(TemporalType.TIMESTAMP)
    val date: Date? = null
)

package com.vinhdn.order.manager.entity

import javax.persistence.*

@Entity
@Table(name = "order_product")
data class OrderProduct(
    @Id
    val id: Long = 0,

    @Column(name = "order_id")
    val orderId: String = "",

    @Column(name = "product_id")
    val productId: String = "",
    val quantity: Int = 0,
    val price: Long = 0,
    @Column(name = "sale_price")
    val salePrice: Long = 0,
    val total: Long = 0,

    @ManyToOne(cascade = [CascadeType.DETACH], fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    val product: Product? = null
)

package com.vinhdn.order.manager.entity

import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(
    @Id
    var id: String = "",
    val name: String = "",
    val origin: String? = null,
    val price: Long = 0,
    val salePrice: Long = 0,
    val quantity: Int = 0,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    var photos: List<ProductPhoto>? = null
)
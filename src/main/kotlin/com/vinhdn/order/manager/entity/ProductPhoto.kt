package com.vinhdn.order.manager.entity

import javax.persistence.*

@Entity
@Table(name = "product_photo")
data class ProductPhoto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    val id: Int? = null,

    @Column(name = "product_id")
    val productId: String = "",

    val link: String = "",
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    var product: Product? = null
)

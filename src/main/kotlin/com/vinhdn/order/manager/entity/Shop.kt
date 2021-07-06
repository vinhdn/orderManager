package com.vinhdn.order.manager.entity

import javax.persistence.*

@Entity
@Table(name = "shop")
data class Shop(
    @Id
    val id: String = "",
    val name: String = "",
    val address: String? = null,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    var userShops: Set<UserShop>? = null
)

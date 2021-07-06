package com.vinhdn.order.manager.entity

import com.vinhdn.order.manager.entity.key.UserShopKey
import java.io.Serializable
import javax.persistence.*

@Entity
@IdClass(UserShopKey::class)
@Table(name = "user_shop")
data class UserShop(
    @Id
    @Column(name = "username")
    val username: String = "",
    @Id
    @Column(name = "shop_id")
    val shopId: String = "",
    val role: String = "USER",

    @ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    val user: User? = null,

    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    val shop: Shop? = null
) : Serializable
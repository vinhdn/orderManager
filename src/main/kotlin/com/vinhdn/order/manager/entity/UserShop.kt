package com.vinhdn.order.manager.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.vinhdn.order.manager.entity.key.UserShopKey
import java.io.Serializable
import javax.persistence.*

@Entity
@IdClass(UserShopKey::class)
@JsonIgnoreProperties("user", "shop", allowGetters = true)
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
    @JoinColumn(name = "shop_id", updatable = false, insertable = false)
    var shop: Shop? = null
) : Serializable
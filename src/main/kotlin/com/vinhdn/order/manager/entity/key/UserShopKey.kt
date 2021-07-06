package com.vinhdn.order.manager.entity.key

import java.io.Serializable
import javax.persistence.Column

data class UserShopKey(
    @Column
    val username: String = "",
    @Column(name = "shop_id")
    val shopId: String = ""
): Serializable {
}
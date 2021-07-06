package com.vinhdn.order.manager.repository

import com.vinhdn.order.manager.entity.UserShop
import com.vinhdn.order.manager.entity.key.UserShopKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserShopRepository: JpaRepository<UserShop, UserShopKey> {
}
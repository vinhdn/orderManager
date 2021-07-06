package com.vinhdn.order.manager.repository

import com.vinhdn.order.manager.entity.Shop
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopRepository: JpaRepository<Shop, String> {
}
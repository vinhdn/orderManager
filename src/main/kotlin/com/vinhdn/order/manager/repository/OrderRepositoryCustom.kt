package com.vinhdn.order.manager.repository

import com.vinhdn.order.manager.entity.UserOrder


enum class OrderStatus(val status: Int) {
    PREPARING(0),
    SHIPPING(1),
    FINISHED(2),
    REFUND(3),
}

interface OrderRepositoryCustom {
    suspend fun findOrderByStatus(status: OrderStatus): List<UserOrder>
}
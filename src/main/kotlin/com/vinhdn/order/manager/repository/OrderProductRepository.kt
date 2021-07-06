package com.vinhdn.order.manager.repository

import com.vinhdn.order.manager.entity.OrderProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderProductRepository: JpaRepository<OrderProduct, Long>
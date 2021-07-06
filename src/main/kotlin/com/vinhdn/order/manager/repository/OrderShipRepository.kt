package com.vinhdn.order.manager.repository

import com.vinhdn.order.manager.entity.OrderShip
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderShipRepository: JpaRepository<OrderShip, Int>
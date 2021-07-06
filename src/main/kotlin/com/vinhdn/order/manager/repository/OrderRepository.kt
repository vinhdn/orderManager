package com.vinhdn.order.manager.repository

import com.vinhdn.order.manager.entity.UserOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<UserOrder, String>, OrderRepositoryCustom
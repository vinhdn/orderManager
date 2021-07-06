package com.vinhdn.order.manager.repository.impl

import com.vinhdn.order.manager.entity.Order
import com.vinhdn.order.manager.repository.OrderRepository
import com.vinhdn.order.manager.repository.OrderRepositoryCustom
import com.vinhdn.order.manager.repository.OrderStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class OrderRepositoryImpl: OrderRepositoryCustom {

    @Autowired
    lateinit var enityManager: EntityManager

    override suspend fun findOrderByStatus(status: OrderStatus): List<Order> {

        return emptyList()
    }

}

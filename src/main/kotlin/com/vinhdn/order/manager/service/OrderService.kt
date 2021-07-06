package com.vinhdn.order.manager.service

import com.vinhdn.order.manager.entity.Order
import com.vinhdn.order.manager.repository.OrderRepository
import com.vinhdn.order.manager.repository.OrderStatus
import kotlinx.coroutines.reactor.flux
import kotlinx.coroutines.reactor.mono
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService {

    @Autowired
    lateinit var orderRepository: OrderRepository

    suspend fun getAllOrder() = mono {
        orderRepository.findAll()
    }

    suspend fun getInProcessingOrder() = mono {
        orderRepository.findOrderByStatus(OrderStatus.PREPARING)
    }

    suspend fun insertOrder(order: Order) = mono {
        orderRepository.save(order)
    }
}
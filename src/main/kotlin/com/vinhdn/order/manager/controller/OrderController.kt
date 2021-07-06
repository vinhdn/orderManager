package com.vinhdn.order.manager.controller

import com.vinhdn.order.manager.entity.UserOrder
import com.vinhdn.order.manager.service.OrderService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitFirstOrNull
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/order")
class OrderController {
    @Autowired
    lateinit var orderService: OrderService

    @PostMapping("create")
    suspend fun createOrder(@RequestBody order: UserOrder) = coroutineScope {
        order.id = RandomString.make(15)
        val orderResult = orderService.createOrder(order).awaitFirstOrNull()
            ?: return@coroutineScope ResponseEntity.badRequest()
        ResponseEntity.ok(orderResult)
    }

    @GetMapping("")
    suspend fun getAllOrder() = coroutineScope {
        ResponseEntity.ok(orderService.getAllOrder().awaitFirstOrNull())
    }
}
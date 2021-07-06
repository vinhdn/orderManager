package com.vinhdn.order.manager.controller

import com.vinhdn.order.manager.entity.Customer
import com.vinhdn.order.manager.service.CustomerService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitFirstOrNull
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/customer")
class CustomerController {
    @Autowired
    lateinit var customerService: CustomerService

    @PostMapping("create")
    suspend fun createOrder(@RequestBody customer: Customer) = coroutineScope {
        customer.id = RandomString.make(15)
        val orderResult = customerService.createCustomer(customer).awaitFirstOrNull()
            ?: return@coroutineScope ResponseEntity.badRequest()
        ResponseEntity.ok(orderResult)
    }

    @GetMapping("")
    suspend fun getAllCustomer() = coroutineScope {
        ResponseEntity.ok(customerService.findAllCustomer())
    }
}
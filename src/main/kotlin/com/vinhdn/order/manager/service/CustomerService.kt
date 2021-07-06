package com.vinhdn.order.manager.service

import com.vinhdn.order.manager.entity.Customer
import com.vinhdn.order.manager.repository.CustomerRepository
import kotlinx.coroutines.reactor.mono
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    fun findCustomerByNameOrPhone(query: String) = mono {
        customerRepository.findAllByNameContainsOrPhoneContains(query, query)
    }

    fun findAllCustomer() = mono {
        customerRepository.findAll()
    }

    fun customerById(id: String) = mono {
        customerRepository.findById(id)
    }

    fun createCustomer(customer: Customer) = mono {
        customerRepository.save(customer)
    }
}
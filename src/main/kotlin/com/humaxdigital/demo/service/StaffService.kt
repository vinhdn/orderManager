package com.humaxdigital.demo.service

import com.humaxdigital.demo.entity.Staff
import com.humaxdigital.demo.repository.StaffRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class StaffService {

    @Autowired
    lateinit var staffRepository: StaffRepository

    @Autowired
    @Qualifier("president")
    lateinit var president: Staff

    fun getStaffByName(name: String): Staff? {
        return staffRepository.getStaffByName(name)
    }
}
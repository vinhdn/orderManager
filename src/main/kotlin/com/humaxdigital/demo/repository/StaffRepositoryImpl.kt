package com.humaxdigital.demo.repository

import com.humaxdigital.demo.entity.Staff
import org.springframework.stereotype.Repository

@Repository
class StaffRepositoryImpl: StaffRepository {

    override fun getStaffByName(name: String): Staff? {
        return Staff(name)
    }
}
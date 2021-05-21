package com.humaxdigital.demo.repository

import com.humaxdigital.demo.entity.Staff

interface StaffRepository {
    fun getStaffByName(name: String): Staff?
}
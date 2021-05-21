package com.humaxdigital.demo.repository

import com.humaxdigital.demo.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository: JpaRepository<Todo, Int> {
    fun findAllByDeleted(isDeleted: Boolean = false): ArrayList<Todo>
}
package com.humaxdigital.demo.service

import com.humaxdigital.demo.entity.Todo
import com.humaxdigital.demo.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoService {

    @Autowired
    lateinit var todoRepository: TodoRepository

    fun getTodoList(): ArrayList<Todo> {
        return todoRepository.findAllByDeleted()
    }

    fun insert(todo: Todo): Todo? {
        return todoRepository.save(todo)
    }

    fun delete(todo: Todo) {

    }
}
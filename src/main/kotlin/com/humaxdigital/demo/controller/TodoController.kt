package com.humaxdigital.demo.controller

import com.humaxdigital.demo.entity.Todo
import com.humaxdigital.demo.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CopyOnWriteArrayList

@RestController
class TodoController {

    @Autowired
    lateinit var todoService: TodoService

    val listTodo: CopyOnWriteArrayList<Todo> = CopyOnWriteArrayList()

    @GetMapping("addTodo")
    fun addTodo(model: Model): String {
        model.addAttribute("todo", Todo())
        return "addTodo"
    }

    @PostMapping("addTodo")
    fun addTodo(@ModelAttribute todo: Todo?): String {
        todo?.let {
            if (todoService.insert(it) != null) {
                return "success"
            }
        }
        return "error"
    }

    @GetMapping("listTodo")
    fun index(model: Model, @RequestParam(value = "limit", required = false, defaultValue = "10") limit: Int = 10): ArrayList<Todo> {
        val list = todoService.getTodoList()
        model.addAttribute("todoList", if(list.size < limit) list else list.subList(0, limit))
        model.addAttribute("lng", "vi")
        return list
    }
}
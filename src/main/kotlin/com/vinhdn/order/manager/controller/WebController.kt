package com.vinhdn.order.manager.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.ui.Model

import org.springframework.web.bind.annotation.RequestParam

@Controller
class WebController {

    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/about") // Nếu người dùng request tới địa chỉ "/about"
    fun about(): String? {
        return "about" // Trả về file about.html
    }

    @GetMapping("/hello")
    fun hello( // Request param "name" sẽ được gán giá trị vào biến String
        @RequestParam(
            name = "name",
            required = false,
            defaultValue = ""
        ) name: String?,  // Model là một object của Spring Boot, được gắn vào trong mọi request.
        model: Model
    ): String? {
        // Gắn vào model giá trị name nhận được
        model.addAttribute("name", name)
        return "hello" // trả về file hello.html cùng với thông tin trong object Model
    }
}
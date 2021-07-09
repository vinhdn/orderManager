package com.vinhdn.order.manager.config

import com.vinhdn.order.manager.extension.CtrlStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobeExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handler(exception: Exception): Map<String, Any?>? {
        val map: MutableMap<String, Any?> = HashMap()
        map["message"] = exception.message
        map["status"] = CtrlStatus.ERR_EXCEPTION
        return map
    }

}
package com.vinhdn.order.manager.interceptor

import com.vinhdn.order.manager.context.CommonContext
import com.vinhdn.order.manager.exception.UnauthorizedException
import com.vinhdn.order.manager.service.AuthService
import kotlinx.coroutines.*
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CommonInterceptor: HandlerInterceptor {

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var commonContext: CommonContext

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        return runBlocking {
            val username =  authService.checkToken(request).awaitFirst()
            val user = authService.findUserByUsername(username).awaitFirstOrNull() ?: throw UnauthorizedException("User not found")
            commonContext.user = user
            user.userShops?.firstOrNull()?.let { commonContext.shop = it.shop }
            true
        }
    }
}
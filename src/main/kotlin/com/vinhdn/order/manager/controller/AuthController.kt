package com.vinhdn.order.manager.controller

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.vinhdn.order.manager.extension.responseNotFound
import com.vinhdn.order.manager.extension.responseSuccess
import com.vinhdn.order.manager.extension.responseValidate
import com.vinhdn.order.manager.property.AuthProperties
import com.vinhdn.order.manager.service.AuthService
import com.vinhdn.order.manager.util.StrongPassword
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("v1/api/auth")
class AuthController {

    @Autowired
    lateinit var authService: AuthService

    @Autowired
    lateinit var authProperties: AuthProperties

    @PostMapping("login")
    suspend fun login(@RequestBody login: Map<String, String>) = coroutineScope {
        val username = login["username"]
        val password = login["password"]
        if (username.isNullOrBlank() || password.isNullOrBlank()) return@coroutineScope responseValidate("Username and password must be not null")
        println("Hash: ${StrongPassword.generateStrongPasswordHash(password)}")
        val user = authService.findUserByUsername(username).awaitFirstOrNull()
            ?: return@coroutineScope responseNotFound("User not found")
        if (StrongPassword.validatePassword(password, user.password!!)) {
            createToken(username)
        } else {
            responseValidate("Password is wrong")
        }
    }

    @PostMapping("refreshToken")
    suspend fun refreshToken(@RequestParam("refreshToken") token: String) = coroutineScope {
        if (token.isBlank()) return@coroutineScope responseValidate("Refresh token must be not null")
        val username = authService.verifyToken(token, true).awaitFirst()
        createToken(username)
    }

    private fun createToken(username: String) = run {
        val expireDate = Date.from(Instant.now().plusSeconds(1 * 24 * 60 * 60))
        val jwtToken: String = JWT.create()
            .withSubject(username)
            .withKeyId(authProperties.clientId)
            .withIssuer("token")
            .withIssuedAt(Date())
            .withExpiresAt(expireDate)
            .sign(Algorithm.HMAC256(authProperties.clientSecret))

        val refreshToken: String = JWT.create()
            .withSubject(username)
            .withKeyId(authProperties.clientId)
            .withIssuer("refreshToken")
            .withIssuedAt(Date())
            .withExpiresAt(Date.from(Instant.now().plusSeconds(7 * 24 * 60 * 60)))
            .sign(Algorithm.HMAC256(authProperties.clientSecret))

        responseSuccess(
            "data", mapOf(
                "token" to jwtToken,
                "refreshToken" to refreshToken,
                "expireAt" to expireDate
            )
        )
    }
}
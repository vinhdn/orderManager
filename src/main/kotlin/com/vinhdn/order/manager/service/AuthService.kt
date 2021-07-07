package com.vinhdn.order.manager.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.vinhdn.order.manager.exception.UnauthorizedException
import com.vinhdn.order.manager.property.AuthProperties
import com.vinhdn.order.manager.repository.UserRepository
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactor.mono
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class AuthService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var authProperties: AuthProperties

    fun findUserByUsername(username: String) = mono {
        userRepository.findByIdOrNull(username)
    }

    suspend fun checkToken(request: HttpServletRequest) = mono {
        val auth = request.getHeader("authorization")
        if (auth.isNullOrBlank()) throw UnauthorizedException("Token notfound")
        val tokens = auth.split(" ")
        if (tokens.size != 2) {
            throw UnauthorizedException("Token is invalid")
        }
        val token = tokens[1]
        verifyToken(token).awaitFirst()
    }

    suspend fun verifyToken(token: String, isRefreshToken: Boolean = false) = mono {
        val jwt = JWT.decode(token)
        if (jwt.issuer == "refreshToken" && !isRefreshToken) throw UnauthorizedException("Token is invalid")
        if (jwt.issuer == "token" && isRefreshToken) throw UnauthorizedException("Token is invalid")
        if (jwt.expiresAt == null || Date().after(jwt.expiresAt)) {
            if (isRefreshToken) {
                throw UnauthorizedException("RefreshToken was Expired")
            } else {
                throw UnauthorizedException("Token was Expired")
            }
        }
        if(jwt.keyId != authProperties.clientId) throw UnauthorizedException("Invalid token")
        val algorithm = Algorithm.HMAC256(authProperties.clientSecret)
        try {
            algorithm.verify(jwt)
        } catch (e: Exception) {
            throw UnauthorizedException(null, e.cause)
        }
        jwt.subject
    }
}
package com.example.jfilmsspring.config.secutiry.jwt

import com.example.jfilmsspring.dto.UsuarioLoginDto
import com.example.jfilmsspring.models.Usuario
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

private val logger = KotlinLogging.logger {}

class JwtAuthenticationFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val authenticationManagerX: AuthenticationManager,
) : UsernamePasswordAuthenticationFilter() {


    override fun attemptAuthentication(req: HttpServletRequest, response: HttpServletResponse): Authentication {
        logger.info { "Intentando autenticar" }

        val credentials = ObjectMapper().readValue(req.inputStream, UsuarioLoginDto::class.java)
        val authorization = UsernamePasswordAuthenticationToken(
            credentials.name,
            credentials.password,
        )
        return authenticationManagerX.authenticate(authorization)
    }

    override fun successfulAuthentication(
        req: HttpServletRequest?, res: HttpServletResponse, chain: FilterChain?,
        auth: Authentication
    ) {
        logger.info { "Autenticación correcta" }


        val user = auth.principal as Usuario
        val token: String = jwtTokenUtil.generateToken(user)
        res.addHeader("Authorization", token)
        res.addHeader("Access-Control-Expose-Headers", JwtTokenUtil.TOKEN_HEADER)
    }


    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        logger.info { "Autenticación incorrecta" }

        val error = BadCredentialsError()
        response.status = error.status
        response.contentType = "application/json"
        response.writer.append(error.toString())
    }

}

private data class BadCredentialsError(
    val timestamp: Long = Date().time,
    val status: Int = 401,
    val message: String = "Usuario o password incorrectos",
) {
    override fun toString(): String {
        return ObjectMapper().writeValueAsString(this)
    }
}
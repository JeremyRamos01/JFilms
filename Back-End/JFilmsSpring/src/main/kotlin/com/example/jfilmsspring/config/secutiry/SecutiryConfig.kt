package com.example.jfilmsspring.config.secutiry

import com.example.jfilmsspring.config.secutiry.jwt.JwtAuthenticationFilter
import com.example.jfilmsspring.config.secutiry.jwt.JwtAuthorizationFilter
import com.example.jfilmsspring.config.secutiry.jwt.JwtTokenUtil
import com.example.jfilmsspring.services.users.UsuarioServices

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SecurityConfig
@Autowired constructor(
    private val userService: UsuarioServices,
    private val jwtTokenUtil: JwtTokenUtil
) {
    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.userDetailsService(userService)
        return authenticationManagerBuilder.build()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManager = authManager(http)
        http
            .exceptionHandling()
            .and()
            .authenticationManager(authenticationManager)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/error/**").permitAll()
           .requestMatchers("/api/**").permitAll()
            .and()
            .addFilter(JwtAuthenticationFilter(jwtTokenUtil, authenticationManager))
            .addFilter(JwtAuthorizationFilter(jwtTokenUtil, userService, authenticationManager))
            .csrf()
            .disable()
            .cors()

        return http.build()
    }


}




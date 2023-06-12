package com.example.jfilmsspring.controller

import com.example.jfilmsspring.dto.*
import com.example.jfilmsspring.config.APIConfig

import com.example.jfilmsspring.exceptions.UsuarioBadRequestException

import com.example.jfilmsspring.mappers.toDto
import com.example.jfilmsspring.mappers.toModel
import com.example.jfilmsspring.config.secutiry.jwt.JwtTokenUtil
import com.example.jfilmsspring.models.Usuario
import com.example.jfilmsspring.services.users.UsuarioServices
import com.example.microserviciousuarios.validators.validate
import jakarta.validation.Valid

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder

import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


private val logger = KotlinLogging.logger {}
@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping(APIConfig.API_PATH + "/users")
class UsuarioController
@Autowired constructor(
    private val UsuarioService: UsuarioServices,
    private val jwtTokenUtil: JwtTokenUtil,
) {
    @PostMapping("/login")
    suspend fun loginUser(@Valid @RequestBody logginDto: UsuarioLoginDto): ResponseEntity<UsuarioWithTokenDto> {
        logger.info { "logueando" }
      val user = UsuarioService.loadUserByName(logginDto.name)
        val jwtToken: String = jwtTokenUtil.generateToken(user)

        logger.info { "Token: ${jwtToken}" }
        val userToken = UsuarioWithTokenDto(user.toDto(), jwtToken)
        return ResponseEntity.ok(userToken)
}

    @PostMapping("/register")
    suspend fun register(@RequestBody UsuarioCreateDto: UsuarioCreateDto): ResponseEntity<UsuarioWithTokenDto> {
        logger.info { "Registrando a: ${UsuarioCreateDto.name}" }
        try {
            val user = UsuarioCreateDto.validate().toModel()
            var userSaved: Usuario
            if(UsuarioCreateDto.rol!!.contains("ADMIN")){
                userSaved = UsuarioService.save(user, true)
            }else{
                userSaved = UsuarioService.save(user, false)
            }

            val jwtToken: String = jwtTokenUtil.generateToken(userSaved)
            logger.info { "Token del usuario : ${jwtToken} " }
            return ResponseEntity.ok(UsuarioWithTokenDto(userSaved.toDto(), jwtToken))
        } catch (e: UsuarioBadRequestException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }
@GetMapping("/{name}")
    suspend fun getUser(@PathVariable name: String): ResponseEntity<UsuarioDto>{
        var user = UsuarioService.loadUserByName(name)
        return ResponseEntity.ok(user.toDto())
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/me")
    fun meInfo(@AuthenticationPrincipal user: Usuario): ResponseEntity<UsuarioDto> {
        logger.info { "Obteniendo la informacíon  del usuario : ${user.name}" }
        return ResponseEntity.ok(user.toDto())
    }

    @PutMapping("/me/{uuid}")
    suspend fun updateMe(@PathVariable uuid: String, @Valid @RequestBody UsuarioDto: UsuarioUpdateDto):
            ResponseEntity<UsuarioDto> {

        UsuarioDto.validate()
        val userFound = UsuarioService.loadUserbyUuid(uuid)
        val userUpdated = userFound!!.copy(
            email = UsuarioDto.email,
            name = UsuarioDto.name,
            dni = UsuarioDto.dni,
            monto = UsuarioDto.monto!!,
            tarjeta = UsuarioDto.tarjeta
        )
        try {
            val userUpdated = UsuarioService.update(userUpdated)
            return ResponseEntity.ok(userUpdated.toDto())
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }
}


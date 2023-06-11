package com.example.jfilmsspring.services.users

import com.example.jfilmsspring.exceptions.UsuarioBadRequestException
import com.example.jfilmsspring.exceptions.UsuarioNotFoundException
import com.example.jfilmsspring.models.Usuario
import com.example.jfilmsspring.repositories.UsuarioRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

private val logger = KotlinLogging.logger {}


@Service
class UsuarioServices
@Autowired constructor(
    private val repository: UsuarioRepository,
    private val passwordEncoder: PasswordEncoder
): UserDetailsService {

    
    override fun loadUserByUsername(email: String): UserDetails = runBlocking {
        return@runBlocking repository.findByEmail(email).firstOrNull()
            ?: throw UsuarioNotFoundException("Usuario no encontrado con username: $email")
    }

    suspend fun loadUserByName(name: String): Usuario = runBlocking {
        return@runBlocking repository.findByName(name).firstOrNull()
            ?: throw UsuarioNotFoundException("Usuario no encontrado con username: $name")

    }

    suspend fun findAll() = withContext(Dispatchers.IO) {
        return@withContext repository.findAll()
    }


    suspend fun loadUserbyUuid(uuid:String)= withContext(Dispatchers.IO) {
        return@withContext repository.findByUuid(uuid).firstOrNull()
    }

    suspend fun save(user: Usuario, isAdmin: Boolean): Usuario = withContext(Dispatchers.IO) {

        logger.info { "Guardando usuario: $user" }
        if (repository.findByEmail(user.email).firstOrNull() != null) {
            throw UsuarioBadRequestException("El usuario ya existe con este email")
        }

        var userCopy = user.copy(
            uuid = UUID.randomUUID().toString(),
            password = passwordEncoder.encode(user.password),
            rol = Usuario.TypeRol.USER.name,
        )
        if (isAdmin) {
            userCopy = userCopy.copy(
                rol = Usuario.TypeRol.ADMIN.name
            )
        }

        println(userCopy)
        try {
            return@withContext repository.save(userCopy)
        } catch (e: Exception) {
            throw UsuarioBadRequestException("Error al crear el usuario: Nombre de usuario o email ya existen")
        }

    }

    suspend fun update(user: Usuario) = withContext(Dispatchers.IO) {
        logger.info { "Actualizando usuario: $user" }

        var userBBDD = repository.findByName(user.name).firstOrNull()
             if (userBBDD != null && userBBDD.id != user.id) {
            throw UsuarioBadRequestException("El Id ya existe")
        }
            userBBDD = repository.findByEmail(user.email!!).firstOrNull()
        if (userBBDD != null && userBBDD.id != user.id) {
            throw UsuarioBadRequestException("El email ya existe")
        }

        try {
            return@withContext repository.save(user)
        } catch (e: Exception) {
            println(e.message)
            throw UsuarioBadRequestException("Error al actualizar el usuario: Nombre de usuario o email ya existen")
        }

    }
}
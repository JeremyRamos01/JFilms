package com.example.jfilmsspring.controller

import com.example.jfilmsspring.config.APIConfig
import com.example.jfilmsspring.dto.EntradaCreateDto
import com.example.jfilmsspring.dto.EntradaDto
import com.example.jfilmsspring.errorsResult.EntradasError
import com.example.jfilmsspring.mappers.toDto
import com.example.jfilmsspring.mappers.toModel
import com.example.jfilmsspring.services.users.EntradasService
import com.example.jfilmsspring.services.users.ReservaService
import com.example.jfilmsspring.validators.validate
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.get
import com.github.michaelbull.result.mapBoth
import jakarta.validation.Valid
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping(APIConfig.API_PATH + "/entradas")
class EntradasController
@Autowired constructor(
    private val entradasService: EntradasService,
    private val reservaService : ReservaService
) {

    @GetMapping("")
    suspend fun finAll(): ResponseEntity<List<EntradaDto>> {

        val res = entradasService.findAll()
            .toList()
            .map { it.toDto(reservaService.findById(it.reservaId).get()!!) }
        return ResponseEntity.ok(res)
    }
    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: UUID): ResponseEntity<EntradaDto> {
        entradasService.findByUuid(id).mapBoth(
            success = {
                return ResponseEntity.ok(
                    it.toDto(reservaService.findById(it.reservaId).get()!!)
                )
            },
            failure = { return handleErrors(it) }
        )
    }

    @PostMapping("")
    suspend fun create(@Valid @RequestBody entradaDto: EntradaCreateDto): ResponseEntity<EntradaDto> {
        entradaDto.validate().andThen {
            entradasService.save(it.toModel())
        }.mapBoth(
            success = {
                return ResponseEntity.status(HttpStatus.CREATED)
                    .body(it.toDto(reservaService.findById(it.reservaId).get()!!))
            },
            failure = { return handleErrors(it) }
        )
    }

    @PutMapping("/{id}")
    suspend fun update(
        @PathVariable id: UUID,
        @Valid @RequestBody entradaDto: EntradaCreateDto
    ): ResponseEntity<EntradaDto> {

        entradaDto.validate().andThen {
            entradasService.update(id, it.toModel()) }.mapBoth(
            success = {
                return ResponseEntity.ok(
                    it.toDto(reservaService.findById(it.reservaId).get()!!)
                )
            },
            failure = { return handleErrors(it) }
        )
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: Int): ResponseEntity<EntradaDto> {

        entradasService.deleteById(id).mapBoth(
            success = { return ResponseEntity.noContent().build() },
            failure = { return handleErrors(it) }
        )
    }

    private fun handleErrors(entradasError: EntradasError): ResponseEntity<EntradaDto> {
        when (entradasError) {
            is EntradasError.NotFound -> throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                entradasError.message
            )

            is EntradasError.BadRequest -> throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                entradasError.message
            )
        }
    }
}
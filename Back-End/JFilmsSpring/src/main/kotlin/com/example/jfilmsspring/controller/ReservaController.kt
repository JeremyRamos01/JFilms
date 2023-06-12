package com.example.jfilmsspring.controller

import com.example.jfilmsspring.config.APIConfig
import com.example.jfilmsspring.dto.ReservaCreateDto
import com.example.jfilmsspring.dto.ReservaDto
import com.example.jfilmsspring.errorsResult.ReservaError
import com.example.jfilmsspring.mappers.toDto
import com.example.jfilmsspring.mappers.toModel
import com.example.jfilmsspring.services.users.ReservaService
import com.example.jfilmsspring.validators.validate
import com.github.michaelbull.result.andThen
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
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping(APIConfig.API_PATH + "/reserva")
class ReservaController
@Autowired constructor(
    private val reservaService: ReservaService,
) {

    @GetMapping("")
    suspend fun finAll(): ResponseEntity<List<ReservaDto>> {
        val res = reservaService.findAll()
            .toList()
            .map { it.toDto() }
        return ResponseEntity.ok(res)

    }

    @GetMapping ("lista/{uuid}")
    suspend fun findReservasByUserUuid(@PathVariable  uuid: String): ResponseEntity<List<ReservaDto>>{
        val res = reservaService.findByUserUuid(uuid)
            .toList()
            .map { it.toDto() }
        return ResponseEntity.ok(res)
    }

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: UUID): ResponseEntity<ReservaDto> {
        reservaService.findByUuid(id).mapBoth(
            success = {
                return ResponseEntity.ok(
                    it.toDto()
                )
            },
            failure = { return handleErrors(it) }
        )
    }

    @PostMapping("")
    suspend fun create(@Valid @RequestBody reservaDto: ReservaCreateDto): ResponseEntity<ReservaDto> {
        reservaDto.validate().andThen {
            reservaService.save(it.toModel())
        }.mapBoth(
            success = {
                return ResponseEntity.status(HttpStatus.CREATED)
                    .body(it.toDto())
            },
            failure = { return handleErrors(it) }
        )
    }

    @PutMapping("/{id}")
    suspend fun update(
        @PathVariable id: UUID,
        @Valid @RequestBody reservaDto: ReservaCreateDto
    ): ResponseEntity<ReservaDto> {

        reservaDto.validate().andThen {
            reservaService.update(id, it.toModel()) }.mapBoth(
            success = {
                return ResponseEntity.ok(
                    it.toDto()
                )
            },
            failure = { return handleErrors(it) }
        )
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: Int): ResponseEntity<ReservaDto> {

        reservaService.deleteById(id).mapBoth(
            success = { return ResponseEntity.noContent().build() },
            failure = { return handleErrors(it) }
        )
    }

    private fun handleErrors(reservaError: ReservaError): ResponseEntity<ReservaDto> {
        when (reservaError) {
            is ReservaError.NotFound -> throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                reservaError.message
            )

            is ReservaError.BadRequest -> throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                reservaError.message
            )
        }
    }
}
package com.example.jfilmsspring.controller

import com.example.jfilmsspring.config.APIConfig
import com.example.jfilmsspring.dto.SalaCreateDto
import com.example.jfilmsspring.dto.SalaDto
import com.example.jfilmsspring.errorsResult.SalasError
import com.example.jfilmsspring.mappers.toDto
import com.example.jfilmsspring.mappers.toModel
import com.example.jfilmsspring.services.users.SalasService
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
@RequestMapping(APIConfig.API_PATH + "/salas")
class SalasController
@Autowired constructor(
    private val salasService: SalasService,
) {

    @GetMapping("")
    suspend fun finAll(): ResponseEntity<List<SalaDto>> {

        val res = salasService.findAll()
            .toList()
            .map { it.toDto() }
        return ResponseEntity.ok(res)

    }

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: UUID): ResponseEntity<SalaDto> {
        salasService.findByUuid(id).mapBoth(
            success = {
                return ResponseEntity.ok(
                    it.toDto()
                )
            },
            failure = { return handleErrors(it) }
        )
    }

    @GetMapping("/pelicula/{id}")
    suspend fun finAllByPelicula(@PathVariable id : Int): ResponseEntity<List<SalaDto>> {

        val res = salasService.findByPeliculaId(id)
            .toList()
            .map { it.toDto() }
        return ResponseEntity.ok(res)

    }

    @PostMapping("")
    suspend fun create(@Valid @RequestBody salaDto: SalaCreateDto): ResponseEntity<SalaDto> {
        salaDto.validate().andThen {
            salasService.save(it.toModel())
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
        @Valid @RequestBody salaDto: SalaCreateDto
    ): ResponseEntity<SalaDto> {

        salaDto.validate().andThen {
            salasService.update(id, it.toModel()) }.mapBoth(
            success = {
                return ResponseEntity.ok(
                    it.toDto()
                )
            },
            failure = { return handleErrors(it) }
        )
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: Int): ResponseEntity<SalaDto> {

        salasService.deleteById(id).mapBoth(
            success = { return ResponseEntity.noContent().build() },
            failure = { return handleErrors(it) }
        )
    }

    private fun handleErrors(salasError: SalasError): ResponseEntity<SalaDto> {
        when (salasError) {
            is SalasError.NotFound -> throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                salasError.message
            )

            is SalasError.BadRequest -> throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                salasError.message
            )
        }
    }
}
package com.example.jfilmsspring.controller

import com.example.jfilmsspring.config.APIConfig
import com.example.jfilmsspring.dto.ButacaCreateDto
import com.example.jfilmsspring.dto.ButacaDto
import com.example.jfilmsspring.errorsResult.ButacasError
import com.example.jfilmsspring.mappers.toDto
import com.example.jfilmsspring.mappers.toModel
import com.example.jfilmsspring.services.users.ButacasService
import com.example.jfilmsspring.services.users.SalasService
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
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping(APIConfig.API_PATH + "/butacas")
class ButacasController
@Autowired constructor(
    private val butacasService: ButacasService,
    private val salasService: SalasService
) {

    @GetMapping("")
    suspend fun finAll(): ResponseEntity<List<ButacaDto>> {

        val res = butacasService.findAll()
            .toList()
            .map { it.toDto(salasService.findById(it.salaId).get()!!)}
        return ResponseEntity.ok(res)

    }

    @GetMapping("/especifica/{fila}/{numero}")
    suspend fun findByFilaAndNumero(@PathVariable fila: Int, @PathVariable numero: Int): ResponseEntity<ButacaDto>{
        butacasService.findByFilaAndNumero(fila,numero).mapBoth(
            success = {
                return ResponseEntity.ok(
                    it.toDto(salasService.findById(it.salaId).get()!!)
                )
            },
            failure = { return handleErrors(it) }
        )
    }

    @GetMapping("/butacasPorSala/{salaId}")
    suspend fun finAllBySalaId(@PathVariable salaId: Int): ResponseEntity<List<ButacaDto>> {

        val res = butacasService.findBySalaId(salaId)
            .toList()
            .map { it.toDto(salasService.findById(it.salaId).get()!!) }
        return ResponseEntity.ok(res)
    }

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: UUID): ResponseEntity<ButacaDto> {
        butacasService.findByUuid(id).mapBoth(
            success = {
                return ResponseEntity.ok(
                    it.toDto(salasService.findById(it.salaId).get()!!)
                )
            },
            failure = { return handleErrors(it) }
        )
    }

    @PostMapping("/crear")
    suspend fun create(@Valid @RequestBody butacaDto: ButacaCreateDto): ResponseEntity<ButacaDto> {
        butacaDto.validate().andThen {
            println("pasa")
                butacasService.save(it.toModel())

        }.mapBoth(
            success = {
                return ResponseEntity.status(HttpStatus.CREATED)
                    .body(it.toDto(salasService.findById(it.salaId).get()!!))
            },
            failure = { return handleErrors(it) }
        )
    }

    @PutMapping("/{id}")
    suspend fun update(
        @PathVariable id: Int,
        @Valid @RequestBody butacaDto: ButacaCreateDto
    ): ResponseEntity<ButacaDto> {

        butacaDto.validate().andThen {
            butacasService.update(id, it.toModel()) }.mapBoth(
            success = {
                return ResponseEntity.ok(
                    it.toDto(salasService.findById(it.salaId).get()!!)
                )
            },
            failure = { return handleErrors(it) }
        )
    }

    @DeleteMapping("/{id}")
    suspend fun delete(@PathVariable id: Int): ResponseEntity<ButacaDto> {
        butacasService.deleteById(id).mapBoth(
            success = { return ResponseEntity.noContent().build() },
            failure = { return handleErrors(it) }
        )
    }

    private fun handleErrors(butacasError: ButacasError): ResponseEntity<ButacaDto> {
        when (butacasError) {
            is ButacasError.NotFound -> throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                butacasError.message
            )

            is ButacasError.BadRequest -> throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                butacasError.message
            )
        }
    }
}
package com.alura.challenge.controller

import com.alura.challenge.controller.dto.RevenueDTO
import com.alura.challenge.model.IncomeModel
import com.alura.challenge.service.IncomeService
import com.alura.challenge.extension.mapToRevenueDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping
class RevenueController (
    private var incomeService: IncomeService
) {
    @GetMapping("/revenue/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun getRevenueById(@PathVariable id: String): IncomeModel {
        return incomeService.getIncomeById(id)
    }

    @GetMapping("/revenues")
    @ResponseStatus(HttpStatus.OK)
    fun listRevenues(): List<IncomeModel> {
        return incomeService.listIncomes()
    }

    @PostMapping("/revenue")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerRevenue(
        @Valid @RequestBody revenueDTO: RevenueDTO,
        uriBuilder: UriComponentsBuilder
        ): ResponseEntity<RevenueDTO> {
        val revenueDTO = incomeService.registerIncome(revenueDTO)
        val uri = uriBuilder.path("/expense/${revenueDTO.id}").build().toUri()
        return ResponseEntity.created(uri).body(revenueDTO.mapToRevenueDTO())
    }


    @PutMapping("/revenue/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateRevenue(
        @PathVariable id: String,
        @RequestBody revenueDTO: RevenueDTO
    ): IncomeModel {
        return incomeService.updateIncome(id, revenueDTO)
    }

    @DeleteMapping("/revenue/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRevenue(
        @PathVariable id: String
    ) {
        incomeService.delete(id)
    }





}
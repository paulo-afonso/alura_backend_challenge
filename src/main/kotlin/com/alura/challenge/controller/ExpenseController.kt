package com.alura.challenge.controller

import com.alura.challenge.controller.dto.ExpenseDTO
import com.alura.challenge.model.OutcomeModel
import com.alura.challenge.service.OutcomeService
import com.alura.challenge.extension.mapToExpenseDTO
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
class ExpenseController (
    private val outcomeService: OutcomeService
) {
    @GetMapping("/expense/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun getExpenseById(@PathVariable id: String): OutcomeModel {
        return outcomeService.getOutcomeById(id)
    }

    @GetMapping("/expenses")
    @ResponseStatus(HttpStatus.OK)
    fun listExpenses(): List<OutcomeModel> {
        return outcomeService.listOutcomes()
    }

    @PostMapping("/expense")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerRevenue(
        @Valid @RequestBody expenseDTO: ExpenseDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<ExpenseDTO> {
        val expenseDTO = outcomeService.registerOutcome(expenseDTO)
        val uri = uriBuilder.path("/expense/${expenseDTO.id}").build().toUri()
        return ResponseEntity.created(uri).body(expenseDTO.mapToExpenseDTO())
    }

    @PutMapping("/expense/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateExpense(
        @PathVariable id: String,
        @RequestBody expenseDTO: ExpenseDTO
    ): OutcomeModel {
        return outcomeService.updateOutcome(id, expenseDTO)
    }

    @DeleteMapping("/expense/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRevenue(
        @PathVariable id: String
    ) {
        outcomeService.deleteOutcome(id)
    }





}
package com.alura.challenge.service

import com.alura.challenge.controller.dto.ExpenseDTO
import com.alura.challenge.exception.IncomeNotFoundException
import com.alura.challenge.exception.OutcomeDuplicatedException
import com.alura.challenge.exception.OutcomeNotFoundException
import com.alura.challenge.model.IncomeModel
import com.alura.challenge.model.OutcomeModel
import com.alura.challenge.repository.OutcomeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OutcomeService(
    @Autowired
    var outcomeRepository: OutcomeRepository
) {

    fun registerOutcome(formExpenseDTO: ExpenseDTO): OutcomeModel {
        val monthDescriptionKey = "${formExpenseDTO.date.toString().substring(0, 7)}/${formExpenseDTO.description}"
        if (outcomeRepository.findByDistinctiveKey(monthDescriptionKey) == null) {
            val outcome = OutcomeModel(
                id = null,
                description = formExpenseDTO.description,
                value = formExpenseDTO.value,
                date = formExpenseDTO.date,
                distinctiveKey = monthDescriptionKey
            )
            return outcomeRepository.save(outcome)
        } else {
            throw OutcomeDuplicatedException()
        }

    }

    fun getOutcomeById(id: String): OutcomeModel {
        return outcomeRepository.findByIdOrNull(id) ?: throw OutcomeNotFoundException()
    }

    fun listOutcomes(): List<OutcomeModel> {
        return outcomeRepository.findAll()
    }

    fun updateOutcome(id: String, expenseDTO: ExpenseDTO): OutcomeModel {
        val outcome = getOutcomeById(id)
        val monthDescriptionKey = "${expenseDTO.date.toString().substring(0, 7)}/${expenseDTO.description}"
        if (outcomeRepository.findByDistinctiveKey(monthDescriptionKey) == null) {
            outcome.apply {
                description = expenseDTO.description
                value = expenseDTO.value
                date = expenseDTO.date
                distinctiveKey = monthDescriptionKey
        }
        return outcomeRepository.save(outcome)
        } else {
            throw OutcomeDuplicatedException()
        }

    }

    fun deleteOutcome(id: String) {
        outcomeRepository.delete(getOutcomeById(id))
    }


}
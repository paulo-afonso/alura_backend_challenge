package com.alura.challenge.service

import com.alura.challenge.controller.dto.RevenueDTO
import com.alura.challenge.exception.IncomeDuplicatedException
import com.alura.challenge.exception.IncomeNotFoundException
import com.alura.challenge.model.IncomeModel
import com.alura.challenge.repository.IncomeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class IncomeService(
    @Autowired
    var incomeRepository: IncomeRepository
) {

    fun registerIncome(formRevenueDTO: RevenueDTO): IncomeModel {
        val monthDescriptionKey = "${formRevenueDTO.date.toString().substring(0, 7)}/${formRevenueDTO.description}"
        if (incomeRepository.findByDistinctiveKey(monthDescriptionKey) == null) {
            val income = IncomeModel(
                id = null,
                description = formRevenueDTO.description,
                value = formRevenueDTO.value,
                date = formRevenueDTO.date,
                distinctiveKey = monthDescriptionKey
            )
            return incomeRepository.save(income)
        } else {
            throw IncomeDuplicatedException()
        }
    }

    fun getIncomeById(id: String): IncomeModel {
        return incomeRepository.findByIdOrNull(id) ?: throw IncomeNotFoundException()
    }

    fun listIncomes(): List<IncomeModel> {
        return incomeRepository.findAll()
    }

    fun updateIncome(id: String, revenueDTO: RevenueDTO): IncomeModel {
        val income = getIncomeById(id)
        val monthDescriptionKey = "${revenueDTO.date.toString().substring(0, 7)}/${revenueDTO.description}"
        if (incomeRepository.findByDistinctiveKey(monthDescriptionKey) == null) {
            income.apply {
                description = revenueDTO.description
                value = revenueDTO.value
                date = revenueDTO.date
                distinctiveKey = monthDescriptionKey
            }
            return incomeRepository.save(income)
        } else {
            throw IncomeDuplicatedException()
        }
    }

    fun delete(id: String) {
        incomeRepository.delete(getIncomeById(id))
    }


}
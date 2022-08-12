package com.alura.challenge.extension

import com.alura.challenge.controller.dto.ExpenseDTO
import com.alura.challenge.model.OutcomeModel

fun OutcomeModel.mapToExpenseDTO() = ExpenseDTO(
    description = description,
    date = date!!,
    value = value
)
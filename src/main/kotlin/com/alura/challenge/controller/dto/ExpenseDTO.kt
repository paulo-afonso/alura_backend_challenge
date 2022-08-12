package com.alura.challenge.controller.dto

import java.time.LocalDate

data class ExpenseDTO (
    val description: String,
    val value: Int,
    val date: LocalDate
)
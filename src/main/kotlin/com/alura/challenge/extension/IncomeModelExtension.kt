package com.alura.challenge.extension

import com.alura.challenge.controller.dto.RevenueDTO
import com.alura.challenge.model.IncomeModel

fun IncomeModel.mapToRevenueDTO() = RevenueDTO(
    description = description,
    date = date!!,
    value = value
)
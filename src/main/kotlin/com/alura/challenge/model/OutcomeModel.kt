package com.alura.challenge.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import javax.validation.constraints.NotBlank

@Document
data class OutcomeModel (
    @Id var id: String? = null,
    @field:NotBlank var description: String,
    @field:NotBlank var value: Int,
    @field:NotBlank var date: LocalDate? = LocalDate.now(),
    var distinctiveKey: String
)
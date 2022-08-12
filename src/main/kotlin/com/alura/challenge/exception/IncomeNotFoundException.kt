package com.alura.challenge.exception

import com.alura.challenge.commons.Constants
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND, reason = Constants.REVENUE_NOT_FOUND)
class IncomeNotFoundException : RuntimeException()
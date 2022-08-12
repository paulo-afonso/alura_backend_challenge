package com.alura.challenge.exception

import com.alura.challenge.commons.Constants
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST, reason = Constants.EXPENSE_DUPLICATED)
class OutcomeDuplicatedException: RuntimeException()
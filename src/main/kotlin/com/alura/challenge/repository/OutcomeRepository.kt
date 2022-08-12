package com.alura.challenge.repository

import com.alura.challenge.model.IncomeModel
import com.alura.challenge.model.OutcomeModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OutcomeRepository: MongoRepository<OutcomeModel, String> {

    fun findByDistinctiveKey(distinctiveKey: String): OutcomeModel?

}
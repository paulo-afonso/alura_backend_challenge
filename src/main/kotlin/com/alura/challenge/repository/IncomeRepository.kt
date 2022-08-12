package com.alura.challenge.repository

import com.alura.challenge.model.IncomeModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface IncomeRepository: MongoRepository<IncomeModel, String> {

    fun findByDistinctiveKey(distinctiveKey: String): IncomeModel?

}
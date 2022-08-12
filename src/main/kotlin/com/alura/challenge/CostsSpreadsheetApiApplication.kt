package com.alura.challenge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.util.Date

@SpringBootApplication
class CostsSpreadsheetApiApplication

fun main(args: Array<String>) {
	runApplication<CostsSpreadsheetApiApplication>(*args)
}

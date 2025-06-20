package ru.marchenko.studentportal.dto

import java.time.LocalDate

data class MarkDto(
	val date: LocalDate,
	val mark: Int?,
)

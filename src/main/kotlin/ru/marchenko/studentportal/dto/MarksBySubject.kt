package ru.marchenko.studentportal.dto

import ru.marchenko.studentportal.dto.MarkDto
import ru.marchenko.studentportal.model.Subject


data class MarksBySubject(
	val marks: List<MarkDto>,
	val subject: Subject,
)
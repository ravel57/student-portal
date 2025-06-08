package ru.ravel.studentportal.dto

import ru.ravel.studentportal.model.Mark
import ru.ravel.studentportal.model.Subject
import java.time.LocalDate


data class MarksBySubject(
	val marks: List<Mark>,
	val subject: Subject,
)
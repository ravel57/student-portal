package ru.ravel.studentportal.dto

import ru.ravel.studentportal.model.Subject


data class MarksBySubject(
	val marks: List<MarkDto>,
	val subject: Subject,
)
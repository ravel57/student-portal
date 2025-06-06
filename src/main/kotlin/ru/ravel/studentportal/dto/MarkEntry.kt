package ru.ravel.studentportal.dto

data class MarkEntry(
	val date: String,
	val mark: Int,
	val subjectId: Long,
	val studentId: Long
)
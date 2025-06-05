package ru.ravel.studentportal.dto

data class MarkEntry(
	val student: String,
	val date: String,
	val mark: Int,
	val subject: String,
	val studentId: Long
)

package ru.marchenko.studentportal.dto

data class NewUserDto(
	val firstname: String,
	val lastname: String,
	val email: String,
	val password: String,
	val role: String,
	val groupId: Long? = null,
)
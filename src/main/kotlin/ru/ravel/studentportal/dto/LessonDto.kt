package ru.ravel.studentportal.dto

import java.time.ZonedDateTime


data class LessonDto(
	val subjectId: Long,
	val groupId: Long,
	val date: ZonedDateTime? = null,
)
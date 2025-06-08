package ru.ravel.studentportal.dto

import ru.ravel.studentportal.model.User


data class StudentsMarksBySubjects(
	val student: User,
	val marksBySubject: List<MarksBySubject>,

)
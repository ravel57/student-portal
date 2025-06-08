package ru.ravel.studentportal.model

import jakarta.persistence.*
import java.time.ZonedDateTime


@Entity
data class Lesson(

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Int? = null,

	@ManyToOne(fetch = FetchType.EAGER)
	var subject: Subject? = null,

	@ManyToOne(fetch = FetchType.EAGER)
	var group: StudentGroup? = null,

	var date: ZonedDateTime? = null,
)
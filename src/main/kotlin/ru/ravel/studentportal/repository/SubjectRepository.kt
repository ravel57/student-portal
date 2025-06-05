package ru.ravel.studentportal.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.ravel.studentportal.model.Subject

interface SubjectRepository : JpaRepository<Subject, Long> {

	fun findByName(name: String): Subject?

}
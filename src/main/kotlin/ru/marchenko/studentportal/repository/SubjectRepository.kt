package ru.marchenko.studentportal.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.marchenko.studentportal.model.Subject

interface SubjectRepository : JpaRepository<Subject, Long> {

	fun findByName(name: String): Subject?

}
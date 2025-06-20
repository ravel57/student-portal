package ru.marchenko.studentportal.service

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.marchenko.studentportal.model.Role
import ru.marchenko.studentportal.model.Subject
import ru.marchenko.studentportal.repository.SubjectRepository
import ru.marchenko.studentportal.repository.UserRepository


@Service
class SubjectService(
	private val userRepository: UserRepository,
	private val subjectRepository: SubjectRepository
) {

	fun getSubjects(authentication: Authentication): List<Subject>? {
		val user = userRepository.findByEmail(authentication.name)
		val subjects = subjectRepository.findAll().filter { it.teacher == user || user?.role == Role.ADMIN }
		return subjects
	}

}
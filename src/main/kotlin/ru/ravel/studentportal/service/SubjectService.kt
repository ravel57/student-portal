package ru.ravel.studentportal.service

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.ravel.studentportal.model.Subject
import ru.ravel.studentportal.repository.SubjectRepository
import ru.ravel.studentportal.repository.UserRepository


@Service
class SubjectService(
	private val userRepository: UserRepository,
	private val subjectRepository: SubjectRepository
) {

	fun getSubjects(authentication: Authentication): List<Subject>? {
		val teacher = userRepository.findByEmail(authentication.name)
		val subjects = subjectRepository.findAll().filter { it.teacher == teacher }
		return subjects
	}

}
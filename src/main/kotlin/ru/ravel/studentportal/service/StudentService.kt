package ru.ravel.studentportal.service

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.ravel.studentportal.dto.MarkEntry
import ru.ravel.studentportal.model.*
import ru.ravel.studentportal.repository.GroupRepository
import ru.ravel.studentportal.repository.SubjectRepository
import ru.ravel.studentportal.repository.UserRepository
import java.time.ZonedDateTime


@Service
class StudentService(
	private val userRepository: UserRepository,
	private val subjectRepository: SubjectRepository,
	private val groupRepository: GroupRepository,
) {

	fun getStudents(group: StudentGroup): List<User> {
		return userRepository.findAll()
			.filter { it.role == Role.STUDENT }
			.filter { it.group == group }
	}


	fun setMark(markEntry: MarkEntry): User? {
		val student = userRepository.findAll().find { it.id == markEntry.studentId }
		val subject = subjectRepository.findByName(markEntry.subject)
		var studentMark = student?.studentsMarks?.find { it.subject?.name == subject?.name }
		val mark = Mark(
			subject = subject,
			value = markEntry.mark,
			date = ZonedDateTime.now()
		)
		if (studentMark == null) {
			studentMark = StudentsMarks(
				student = student,
				subject = subject,
				marks = mutableListOf(mark),
			)
			student?.studentsMarks = mutableListOf(studentMark)
		} else {
			studentMark.marks.add(mark)
		}
		student?.let { userRepository.save(it) }
		return student
	}

	fun studentMarks(authentication: Authentication): List<StudentsMarks>? {
		val student = userRepository.findByEmail(authentication.name)
		return student?.studentsMarks
	}

	fun getGroups(authentication: Authentication): List<StudentGroup> {
		val principal = authentication.principal as? User
		val user = userRepository.findByEmail(principal?.username ?: "")!!
		if (user.role == Role.TEACHER || user.role == Role.ADMIN) {
			return groupRepository.findAll()
		} else {
			throw RuntimeException("user not teacher or admin")
		}
	}

}
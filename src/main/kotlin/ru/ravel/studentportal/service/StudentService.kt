package ru.ravel.studentportal.service

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.ravel.studentportal.dto.GroupId
import ru.ravel.studentportal.dto.MarkEntry
import ru.ravel.studentportal.model.*
import ru.ravel.studentportal.repository.GroupRepository
import ru.ravel.studentportal.repository.SubjectRepository
import ru.ravel.studentportal.repository.UserRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Service
class StudentService(
	private val userRepository: UserRepository,
	private val subjectRepository: SubjectRepository,
	private val groupRepository: GroupRepository,
) {

	fun getGroups(authentication: Authentication): List<StudentGroup> {
		val principal = authentication.principal as? User
		val user = userRepository.findByEmail(principal?.username ?: "")!!
		if (user.role == Role.TEACHER || user.role == Role.ADMIN) {
			return groupRepository.findAll()
		} else {
			throw RuntimeException("user not teacher or admin")
		}
	}


	fun getStudents(groupId: GroupId): List<User> {
		return userRepository.findByGroupId(groupId.id)
			.filter { it.role == Role.STUDENT }
	}


	fun updateMark(markEntry: MarkEntry): User? {
		val student = userRepository.findById(markEntry.studentId).orElseThrow()
		val subject = subjectRepository.findById(markEntry.subjectId).orElseThrow()
		var studentMark = student?.studentsMarks?.find { it.subject?.name == subject?.name }
		val mark = Mark(
			subject = subject,
			value = markEntry.mark,
			date = LocalDate.parse(markEntry.date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
		)
		if (studentMark != null) {
			studentMark.marks.add(mark)
		} else {
			studentMark = StudentsMarks(
				student = student,
				subject = subject,
				marks = mutableListOf(mark),
			)
			student?.studentsMarks = mutableListOf(studentMark)
		}
		student?.let { userRepository.save(it) }
		return student
	}


//	fun getStudentsMarks(groupId: String): List<StudentsMarks> {
//		return userRepository.findByGroupId(groupId = groupId.toLong())
//			.filter { it.role == Role.STUDENT }
//			.flatMap { it.studentsMarks }
//	}


	fun setStudentMarks(authentication: Authentication): List<StudentsMarks>? {
		val student = userRepository.findByEmail(authentication.name)
		return student?.studentsMarks
	}

}
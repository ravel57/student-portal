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


@Service
class StudentService(
	private val userRepository: UserRepository,
	private val subjectRepository: SubjectRepository,
	private val groupRepository: GroupRepository,
) {

	fun getStudents(groupId: GroupId): List<User> {
		return userRepository.findByGroupId(groupId.id)
			.filter { it.role == Role.STUDENT }
	}


	fun setMark(markEntry: MarkEntry): User? {
		val student = userRepository.findAll().find { it.id == markEntry.studentId }
		val subject = subjectRepository.findById(markEntry.subjectId).orElseThrow()
		var studentMark = student?.studentsMarks?.find { it.subject?.name == subject?.name }
		val mark = Mark(
			subject = subject,
			value = markEntry.mark,
			date = LocalDate.now() //FIXME дата должна прилетать с фронта
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

	fun getStudentsMarks(groupId: String): List<StudentsMarks> {
//		val localDate = LocalDate.parse(studentsMarksDto.date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
		return userRepository.findByGroupId(groupId = groupId.toLong())
			.filter { it.role == Role.STUDENT }
			.flatMap { it.studentsMarks }
	}


	fun setStudentMarks(authentication: Authentication): List<StudentsMarks>? {
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
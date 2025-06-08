package ru.ravel.studentportal.service

import org.springframework.stereotype.Service
import ru.ravel.studentportal.dto.LessonDto
import ru.ravel.studentportal.model.Lesson
import ru.ravel.studentportal.repository.GroupRepository
import ru.ravel.studentportal.repository.LessonRepository
import ru.ravel.studentportal.repository.SubjectRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Service
class LessonService(
	val lessonRepository: LessonRepository,
	private val subjectRepository: SubjectRepository,
	private val groupRepository: GroupRepository
) {

	fun createLesson(lessonDto: LessonDto): Lesson {
		val lesson = Lesson(
			subject = subjectRepository.findById(lessonDto.subjectId).orElse(null),
			group = groupRepository.findById(lessonDto.groupId).orElse(null),
			date = LocalDate.parse(lessonDto.date, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
		)
		return lessonRepository.save(lesson)
	}

	fun getLessons(): List<Lesson> {
		return lessonRepository.findAll()
	}

}
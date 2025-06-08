package ru.ravel.studentportal.service

import org.springframework.stereotype.Service
import ru.ravel.studentportal.dto.LessonDto
import ru.ravel.studentportal.model.Lesson
import ru.ravel.studentportal.repository.GroupRepository
import ru.ravel.studentportal.repository.LessonRepository
import ru.ravel.studentportal.repository.SubjectRepository


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
			date = lessonDto.date,
		)
		return lessonRepository.save(lesson)
	}

}
package ru.marchenko.studentportal.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.marchenko.studentportal.model.StudentGroup

interface GroupRepository : JpaRepository<StudentGroup, Long> {
}
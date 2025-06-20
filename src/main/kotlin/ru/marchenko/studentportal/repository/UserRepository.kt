package ru.marchenko.studentportal.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.marchenko.studentportal.model.User

interface UserRepository : JpaRepository<User, Long> {

	fun findByEmail(username: String) : User?

	fun findByGroupId(groupId: Long) : List<User>

}
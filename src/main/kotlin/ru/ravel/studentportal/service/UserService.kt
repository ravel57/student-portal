package ru.ravel.studentportal.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.ravel.studentportal.dto.NewUserDto
import ru.ravel.studentportal.model.Role
import ru.ravel.studentportal.model.User
import ru.ravel.studentportal.repository.GroupRepository
import ru.ravel.studentportal.repository.UserRepository


@Service
class UserService(
	private val userRepository: UserRepository,
	private val passwordEncoder: PasswordEncoder,
	private val groupRepository: GroupRepository,
) {

	fun createNewUser(newUserDto: NewUserDto) {
		userRepository.save(
			User(
				email = newUserDto.email,
				firstname = newUserDto.firstname,
				lastname = newUserDto.lastname,
				password = passwordEncoder.encode(newUserDto.password),
				role = Role.valueOf(newUserDto.role),
				group = newUserDto.groupId?.let { groupRepository.findById(it).orElse(null) },
			)
		)
	}

}
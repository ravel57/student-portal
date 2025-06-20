package ru.marchenko.studentportal.model

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority {
	STUDENT,
	TEACHER,
	ADMIN,
	;

	override fun getAuthority(): String {
		return "ROLE_${name}"
	}
}
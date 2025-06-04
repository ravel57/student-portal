package ru.ravel.studentportal.model

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority {
	USER,
	TEACHER,
	CURATOR,
	ADMIN,
	;

	override fun getAuthority(): String {
		return "ROLE_${name}"
	}
}
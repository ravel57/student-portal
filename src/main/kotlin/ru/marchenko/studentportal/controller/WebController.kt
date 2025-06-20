package ru.marchenko.studentportal.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller("")
class WebController {

	@GetMapping("")
	fun mainPage(): String {
		return "index"
	}

}
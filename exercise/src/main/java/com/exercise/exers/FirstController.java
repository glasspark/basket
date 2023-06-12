package com.exercise.exers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

	@GetMapping("/hihi")
	public String myfirst() {
		return "hi";
	}
}

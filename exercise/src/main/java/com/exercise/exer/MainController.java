package com.exercise.exer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.model.Model;

@Controller
public class MainController {

	// http://localhost:8082/exer
	@GetMapping("/exer")
	@ResponseBody
	/*
	 * public void index() { // System.out.println("index"); 이렇게 하면 콘솔에만 출력된다. }
	 */

	public String index() {
		return "index 워후 스프링부트 ";
	}

	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}

	

}

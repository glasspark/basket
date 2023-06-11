package com.mysite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	@GetMapping("/")
	public String root() {
	 return "redirect:/product/list"; // 페이지로의 리다이렉션
	}

}

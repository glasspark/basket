package com.mysite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class helloController {

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "hi i am test site";
	}

}

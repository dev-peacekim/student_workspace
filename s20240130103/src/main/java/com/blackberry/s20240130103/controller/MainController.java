package com.blackberry.s20240130103.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String indexPage() {
		return "thymeleaf/index";
	}
	
	@GetMapping(value = "main")
	public String mainPage() {
		return "main";
	}
	
	@GetMapping("projectAdd")
	public String projectAdd() {
		return "projectAdd";
	}
	
}

package com.blackberry.s20240130103.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	@GetMapping("/")
	public String indexPage() {
		return "thymeleaf/index";
	}
	
	@GetMapping(value = "main")
	public String mainPage() {
		return "forward:mainLogic";
	}
}

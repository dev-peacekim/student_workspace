package com.blackberry.s20240130103.yhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AskController {

	@GetMapping(value = "ask")
	public String askPage() {
		return "yhs/ask";
	}
	
	@RequestMapping(value = "askWrite")
	public String askWritePage() {
		return "yhs/askWrite";
	}
	
}

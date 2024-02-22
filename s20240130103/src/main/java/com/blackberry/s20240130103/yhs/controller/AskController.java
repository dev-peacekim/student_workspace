package com.blackberry.s20240130103.yhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AskController {

	@GetMapping(value = "ask")
	public String askPage() {
		return "yhs/ask";
	}
	
}

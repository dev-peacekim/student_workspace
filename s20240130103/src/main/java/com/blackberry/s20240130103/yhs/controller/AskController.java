package com.blackberry.s20240130103.yhs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AskController {

	@GetMapping(value = "ask")
	public String askPage() {
		return "yhs/ask";
	}
	
	@GetMapping(value = "askForm")
	public String askFormPage() {
		return "yhs/askForm";
	}
	
	
	
}

package com.blackberry.s20240130103.lsl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LslController {
	
	@GetMapping(value = "boardFree")
	public String boardfree() {
		return "lsl/boardFree";
	}
	
	@PostMapping(value = "search-bar")
	public String searchbar() {
		return "lsl/boardFree";
	}

}

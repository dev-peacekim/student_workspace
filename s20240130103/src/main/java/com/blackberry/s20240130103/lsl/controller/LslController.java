package com.blackberry.s20240130103.lsl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LslController {
	
	@GetMapping(value = "boardFree")
	public String boardFree() {
		return "lsl/boardFree";
	}
	

}

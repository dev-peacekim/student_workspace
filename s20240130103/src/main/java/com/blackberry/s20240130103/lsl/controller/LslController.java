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
	
	@GetMapping(value = "boardAsk")
	public String boardAsk() {
		return "lsl/boardAsk";
	}
	
	@GetMapping(value = "boardWrite" )
	public String boardWrite() {
		return "lsl/boardWrite";
	}

}

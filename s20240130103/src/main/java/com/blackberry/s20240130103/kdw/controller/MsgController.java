package com.blackberry.s20240130103.kdw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MsgController {

	@GetMapping(value = "msgReceivebox")
	public String receivebox() {
		return "kdw/msgReceivebox";
	}
	
}

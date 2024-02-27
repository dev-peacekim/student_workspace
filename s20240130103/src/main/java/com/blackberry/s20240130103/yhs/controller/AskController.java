package com.blackberry.s20240130103.yhs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blackberry.s20240130103.yhs.model.Ask;
import com.blackberry.s20240130103.yhs.service.AskServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AskController {
	private final AskServiceImpl askService;
	
	@GetMapping(value="ask")
	public String askPage() {
		return "yhs/ask";
	}

	/*
	 * @GetMapping(value = "ask") public String askPage() { return "yhs/ask"; }
	 */
	
	@GetMapping(value = "askForm")
	public String askFormPage() {
		return "yhs/askForm";
	}
	
	/*
	 * @GetMapping(value="ask") public String listAsk(Model model) {
	 * System.out.println("AskController listAsk start..."); List<Ask> askList =
	 * askService.getListAllAsk(); model.addAttribute("ask",askList); return
	 * "yhs/ask"; }
	 */
	
}

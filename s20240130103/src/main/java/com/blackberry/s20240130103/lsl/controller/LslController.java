package com.blackberry.s20240130103.lsl.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LslController {
	private final LslService ls;
	
	
	
	@GetMapping(value = "boardFree")
	public String boardFreeList(LslBoardComm boardComm, Model model) {
		System.out.println("LslController boardfreeList Start...");
		List<LslBoardComm> boardFreeList = ls.getBoardFreeList(boardComm);
		model.addAttribute("boardFreeList", boardFreeList);
		return "lsl/boardFree";
	}
	
	@GetMapping(value = "boardAsk")
	public String boardAsk() {
		return "lsl/boardAsk";
	}
	
	@GetMapping(value = "boardFreeWrite" )
	public String boardFreeWrite(Model model) {
		System.out.println("LslController boardFreeWrite Start...");
		model.addAttribute("LslCommReply");
		return "lsl/boardFreeWrite";
	}
	/*
	 * @PostMapping(value = "boardFreeWrite") public String
	 * submitWriteForm(@ModelAttribute("LslBoardComm") LslBoardComm boardComm) {
	 * System.out.println("LslController submitWriteForm Start...");
	 * 
	 * }
	 */
	
	@GetMapping(value = "boardFreeContents" )
	public String boardFreeContents() {
		return "lsl/boardFreeContents";
	}
	
	@GetMapping(value = "boardFreeModify")
	public String boardFreeModify() {
		return "lsl/boardFreeModify";
	}
}

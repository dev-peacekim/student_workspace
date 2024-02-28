package com.blackberry.s20240130103.lsl.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.blackberry.s20240130103.lsl.Service.BoardFreeAskPaging;
import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LslController {
	private final LslService ls;

	// 자유 게시판 리스트 및 Paging
	@GetMapping(value = "boardFree")
	public String boardFreeList(Model model, LslBoardComm lslBoardComm) {
		System.out.println("LslController boardFreeList Start...");
		int totalBoardFree = ls.totalBoardFree();
		System.out.println("LslController totalBoardFreeList ->" + totalBoardFree);

		// paging 작업
		BoardFreeAskPaging bfpage = new BoardFreeAskPaging(totalBoardFree, lslBoardComm.getCurrentPage());
		lslBoardComm.setStart(bfpage.getStart());
		lslBoardComm.setEnd(bfpage.getEnd());
		System.out.println(lslBoardComm);
		
		// 자유게시판 리스트  
		List<LslBoardComm> boardFreeList = ls.boardFreeList(lslBoardComm);
		System.out.println("LslController boardFreeList.size() ->" + boardFreeList.size());
		
		model.addAttribute("totalBoardFree", totalBoardFree);
		model.addAttribute("boardFreeList", boardFreeList);
		model.addAttribute("bfpage", bfpage);

		return "lsl/boardFree";
	}

	// 질문 게시판 리스트 및 페이징
	@GetMapping(value = "boardAsk")
	public String boardAskList(Model model, LslBoardComm lslBoardComm) {
		System.out.println("LslController boardAskList Start...");
		int totalBoardAsk = ls.totalBoardAsk();
		System.out.println("LslController totalBoardAsk ->" + totalBoardAsk);

		// paging 작업
		BoardFreeAskPaging bapage = new BoardFreeAskPaging(totalBoardAsk, lslBoardComm.getCurrentPage());
		lslBoardComm.setStart(bapage.getStart());
		lslBoardComm.setEnd(bapage.getEnd());
		System.out.println(lslBoardComm);

		// 질문 게시판 리스트  
		List<LslBoardComm> boardAskList = ls.boardAskList(lslBoardComm);
		System.out.println("LslController boardFreeList.size() ->" + boardAskList.size());
	
		model.addAttribute("totalBoardAsk", totalBoardAsk);
		model.addAttribute("boardAskList", boardAskList);
		model.addAttribute("bapage", bapage);
		
		return "lsl/boardAsk";
	}

	
	// 글 작성 페이지
	@GetMapping(value = "boardFreeWrite")
	public String boardFreeWrite() {

		return "lsl/boardFreeWrite";

	}

	// 자유게시판 글 작성
	@PostMapping(value = "boardWrite")
	public String freeBoardWrite(HttpServletRequest request, LslBoardComm lslBoardComm) {
		HttpSession session = request.getSession();
		Long user_no = (Long) session.getAttribute("user_no");
		int insertResult = ls.freeBoardWrite(lslBoardComm, user_no);
		if (insertResult > 0)
			return "redirect:boardFreeList";
		else
			return "redirect:boardFree";
	}

}

package com.blackberry.s20240130103.lsl.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blackberry.s20240130103.lsl.Service.BoardFreeAskPaging;
import com.blackberry.s20240130103.lsl.Service.LslService;
import com.blackberry.s20240130103.lsl.model.LslBoardComm;

import groovyjarjarantlr4.v4.parse.GrammarTreeVisitor.mode_return;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LslController {
	private final LslService ls;

	// 자유 게시판 리스트 및 Paging
	@RequestMapping(value = "boardFree")
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
	
	// 자유게시판검색
	@RequestMapping(value = "boardFreeSearch")
	public String boardFreeSearch (LslBoardComm lslBoardComm, Model model) {
		System.out.println("LslController boardFreeList Start...");
		int totalBoardFree = ls.totalBoardFree();
		System.out.println("LslController totalBoardFreeList ->" + totalBoardFree);

		// paging 작업
		BoardFreeAskPaging bfpage = new BoardFreeAskPaging(totalBoardFree, lslBoardComm.getCurrentPage());
		lslBoardComm.setStart(bfpage.getStart());
		lslBoardComm.setEnd(bfpage.getEnd());
		System.out.println(lslBoardComm);
	
		
		List<LslBoardComm> boardFreeList = ls.boardFreeSearch(lslBoardComm);
		System.out.println("LslController boardFreeList boardFreeSearch.size() ->" + boardFreeList.size());
		
		model.addAttribute("totalBoardFree", totalBoardFree);
		model.addAttribute("bfpage", bfpage);
		model.addAttribute("boardFreeList", boardFreeList);
		
		return "lsl/boardFree";
	}

	// 질문 게시판 리스트 및 페이징
	@RequestMapping(value = "boardAsk")
	public String boardAskList(Model model, LslBoardComm lslBoardComm) {
		System.out.println("LslController boardAskList Start...");
		int totalBoardAsk = ls.totalBoardAsk();
		System.out.println("LslController totalBoardAsk ->" + totalBoardAsk);

		// paging 작업
		BoardFreeAskPaging bapage = new BoardFreeAskPaging(totalBoardAsk, lslBoardComm.getCurrentPage());
		System.out.println("LslController boardAskList  bapage->"+bapage);
		lslBoardComm.setStart(bapage.getStart());
		lslBoardComm.setEnd(bapage.getEnd());
		System.out.println("LslController boardAskList  lslBoardComm->"+lslBoardComm);

		// 질문 게시판 리스트  
		List<LslBoardComm> boardAskList = ls.boardAskList(lslBoardComm);
		System.out.println("LslController boardFreeList.size() ->" + boardAskList.size());
	
		model.addAttribute("totalBoardAsk", totalBoardAsk);
		model.addAttribute("boardAskList", boardAskList);
		model.addAttribute("bapage", bapage);
		
		return "lsl/boardAsk";
	}
	
	// 질문 게시판 검색
	@RequestMapping(value = "boardAskSearch")
	public String boardAskSearch(LslBoardComm lslBoardComm, Model model) {
		System.out.println("LslController boardAskList Start...");
		int totalBoardAsk = ls.totalBoardAsk();
		System.out.println("LslController totalBoardAsk ->" + totalBoardAsk);

		// paging 작업
		BoardFreeAskPaging bapage = new BoardFreeAskPaging(totalBoardAsk, lslBoardComm.getCurrentPage());
		lslBoardComm.setStart(bapage.getStart());
		lslBoardComm.setEnd(bapage.getEnd());
		System.out.println(lslBoardComm);

		// 질문 게시판 리스트  
		List<LslBoardComm> boardAskList = ls.boardAskSearch(lslBoardComm);
		System.out.println("LslController boardFreeList.size() ->" + boardAskList.size());
	
		model.addAttribute("totalBoardAsk", totalBoardAsk);
		model.addAttribute("boardAskList", boardAskList);
		model.addAttribute("bapage", bapage);
		
		return "lsl/boardAsk";
	}

	
	// 질문 게시판 글 insert
	@PostMapping(value = "boardWrite")
	public String boardAskWrite(HttpServletRequest request, LslBoardComm lslBoardComm ) {
		HttpSession session = request.getSession();
		Long user_no = (Long) session.getAttribute("user_no");
		int insertResult = ls.boardAskWrite(lslBoardComm, user_no);
		
		 
		    if (insertResult > 0) {
		       
		        String boardType = request.getParameter("boardType"); // 보드 타입 가져오기
		        if (boardType != null && boardType.equals("Ask")) {
		            return "redirect:lsl/boardAsk"; // 보드 타입이 ask인 경우 리다이렉트 URL 설정
		        } else {
		        	return "redirect:lsl/boardFree"; // 기본적으로 free 페이지로 리다이렉트
		        }
		    } else {
		    	return "redirect:lsl/boardFreeWrite";
		    }
		
	}

	// 질문 게시판 글 폼(board type)
	@GetMapping(value = "boardFreeWrite")
	public String freeBoardWrite( Model model,@RequestParam(name = "boardtype") String boardtype) {
		System.out.println(boardtype);

		model.addAttribute("board_type", boardtype);
		return "lsl/boardFreeWrite";
	}
	

	// 게시글 상세 내용
	@GetMapping(value = "boardFreeContents")
	public String boardFreeContents(HttpServletRequest request ,Model model) {
		System.out.println("boardFreeContents Start...");
		HttpSession session = request.getSession();
		Long user_no = (Long) session.getAttribute("user_no");
		//int boardFreeDetail = ls.boardFreeDetail(user_no);
		return "lsl/boardFreeContents";
	}
	
	

	// 게시글 수정
	@GetMapping(value = "boardFreeModify")
	public String boardFreeModify() {
		
		return "lsl/boardFreeModify";
	}
	
}

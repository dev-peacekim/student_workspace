package com.blackberry.s20240130103.yhs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blackberry.s20240130103.yhs.model.Ask;
import com.blackberry.s20240130103.yhs.service.AskServiceImpl;
import com.blackberry.s20240130103.yhs.service.Paging;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AskController {
	private final AskServiceImpl askService;
	
	
	@RequestMapping(value = "askList") 
	public String askList(HttpServletRequest request , Model model, Ask ask) {
		System.out.println("AskController askList start...");
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		System.out.println("AskController askList user_no->"+user_no);
		if (ask.getCurrentPage() == null ) ask.setCurrentPage("1");
		// Ask 전체 Count  15
	    int totalAsk =  askService.totalAsk();
		System.out.println("AskController Start totalEmp->"+totalAsk );
//		
		// Paging 작업
		Paging page = new Paging(totalAsk, ask.getCurrentPage());
//		// Parameter ask --> Page만 추가 Setting
		ask.setStart(page.getStart());   // 시작시 1
		ask.setEnd(page.getEnd());       // 시작시 10 
		ask.setUser_no(user_no);
		System.out.println("AskController askList ask->"+ask);


		List<Ask> listAsk = askService.listAsk(ask);
		System.out.println("AskController  listAsk.size()=>" + listAsk.size());
		
		
		model.addAttribute("totalEmp", totalAsk);
		model.addAttribute("listAsk" , listAsk);
		model.addAttribute("page"    , page);

		
		return "yhs/ask";
	}
	
	// 자유 게시판 상세내용
	// 세션이 필요할 때 - >
	@GetMapping(value = "askContent")
	public String askContent(HttpServletRequest request, Model model) {
		String admin_title = request.getParameter("admin_title");
		System.out.println("AskController askContent admin_title->"+admin_title);

		Ask askContent = askService.askContent(admin_title);

		System.out.println("AskController askContent askContent->"+askContent);
//		List<LslCommReply> replyBoardFreeList = ls.replyBoardFreeList(cboard_no);

//		model.addAttribute("replyBoardFreeList", replyBoardFreeList);
		model.addAttribute("askContent", askContent);
		return "yhs/askContent";
	}

	@GetMapping(value = "askForm")
	public String askForm(Model model, @RequestParam(name = "boardtype") String boardtype) {
		System.out.println(boardtype);

		int askForm = askService.askForm(boardtype);

		model.addAttribute("board_type", boardtype);
		if (askForm > 0) {
			return "redirect:ask";
		} else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
		return "forward:askFrom";
	}
	
	}	

}

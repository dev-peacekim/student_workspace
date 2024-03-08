package com.blackberry.s20240130103.yhs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	@GetMapping("askList") 
	public String askList(HttpServletRequest request , Model model, Ask ask) {
		System.out.println("AskController askList start...");
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		System.out.println("AskController askList user_no->"+user_no);
		if (ask.getCurrentPage() == null ) ask.setCurrentPage("1");
		// Ask 전체 Count  15
	    int totalAsk =  askService.totalAsk();
		System.out.println("AskController Start totalAsk->"+totalAsk );
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
		
		
		model.addAttribute("totalAsk", totalAsk);
		model.addAttribute("listAsk" , listAsk);
		model.addAttribute("page"    , page);

		
		return "yhs/ask";
	}
	
	// 자유 게시판 검색
		@RequestMapping(value = "askListSearch")
		public String boardFreeSearch(Ask ask, Model model) {
			System.out.println("AskController askList Start...");
			int totalAsk = askService.totalAsk();
			System.out.println("AskController totalAsk ->" + totalAsk);

			// paging 작업
			Paging bfpage = new Paging(totalAsk, ask.getCurrentPage());
			ask.setStart(bfpage.getStart());
			ask.setEnd(bfpage.getEnd());
			System.out.println(ask);
			

			List<Ask> listAsk = askService.askListSearch(ask);
			System.out.println("AskController askList askListSearch.size() ->" + listAsk.size());

			model.addAttribute("totalBoardFree", totalAsk);
			model.addAttribute("bfpage", bfpage);
			model.addAttribute("askList", listAsk);

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
	public String askForm(HttpServletRequest request, Model model) {
		System.out.println("AskController Start askForm...");
		
		String adminStart = request.getParameter("admin_start");
		// 세션에서 보내는 사람의 아이디 가져오기
        Long userNo = (Long) request.getSession().getAttribute("user_no");
        
     // 모델에 데이터 추가 (세션ID, 유저리스트)
        model.addAttribute("userNo", userNo);

		return "yhs/askForm";
	}

	@PostMapping(value = "askWrite")
	public String askWrite(Ask ask ,Model model,HttpServletRequest request ) {
		System.out.println("AskController Start askWrite..." );
		

		Long userNo = (Long) request.getSession().getAttribute("user_no");
		ask.setUser_no(userNo);
		System.out.println("AskController askWrite ask->" + ask);
		int insertResult = askService.insertAsk(ask);
		System.out.println("AskController insertResult insertResult->"+insertResult );
		
		model.addAttribute("userNo", userNo);
		model.addAttribute("ask", ask);
		return "redirect:/askList";

	}
	@GetMapping(value = "askContentReply")
	public String askContentReply() {
		System.out.println("askContentReply");
		return "yhs/askContentReply";
	}
	
}


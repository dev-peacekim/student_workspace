package com.blackberry.s20240130103.yhs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

//
		List<Ask> listAsk = askService.listAsk(ask);
		System.out.println("AskController  listAsk.size()=>" + listAsk.size());
//		
		
		model.addAttribute("totalEmp", totalAsk);
		model.addAttribute("listAsk" , listAsk);
		model.addAttribute("page"    , page);

		
		return "yhs/ask";
	}
	
	@GetMapping(value = "detailAsk")
	public String detailAsk(Ask ask1 , Model model) {
		System.out.println("AskController Start detailAsk..." );
//		1. AskService안에 detailAsk method 선언
//		   1) parameter : User_id
//		   2) Return      Ask
//
//		2. AskDao   detailAsk method 선언 
////		                    mapper ID   ,    Parameter
//		emp = session.selectOne("tkEmpSelOne",    empno);
//		System.out.println("ask->"+ask1);
		Ask ask = askService.detailAsk(ask1.getUser_no());
		model.addAttribute("ask",ask);

		return "detailAsk";
	}

	@GetMapping(value = "askForm")
	public String askFormPage() {
	
		return "yhs/askForm";
	}
	
	
	

}

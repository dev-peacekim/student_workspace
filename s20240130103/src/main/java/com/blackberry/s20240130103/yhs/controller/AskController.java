package com.blackberry.s20240130103.yhs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blackberry.s20240130103.yhs.model.Ask;
import com.blackberry.s20240130103.yhs.service.AskServiceImpl;
import com.blackberry.s20240130103.yhs.service.Paging;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AskController {
	private final AskServiceImpl askService;
	
	
	@RequestMapping(value = "askList") 
	public String askList(Ask ask , Model model) {
		System.out.println("AskController Start askList..." );
		 if (ask.getCurrentPage() == null ) ask.setCurrentPage("1");
		// Ask 전체 Count  15
	    int totalAsk =  askService.totalAsk();
		System.out.println("AskController Start totalEmp->"+totalAsk );
//		
		// Paging 작업
		Paging page = new Paging(totalAsk, ask.getCurrentPage());
//		// Parameter emp --> Page만 추가 Setting
		ask.setStart(page.getStart());   // 시작시 1
		ask.setEnd(page.getEnd());       // 시작시 10 
//
		List<Ask> listAsk = askService.listAsk(ask);
		System.out.println("AskController  listAsk.size()=>" + listAsk.size());
//		
		
//		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listAsk" , listAsk);
		model.addAttribute("page"    , page);

		
		return "yhs/ask";

	}

	@GetMapping(value = "askForm")
	public String askFormPage() {
		return "yhs/askForm";
	}
	
	
	
}

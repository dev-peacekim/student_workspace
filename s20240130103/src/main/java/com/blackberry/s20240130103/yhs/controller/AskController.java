package com.blackberry.s20240130103.yhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AskController {

	@GetMapping(value = "ask")
	public String askPage() {
		return "yhs/ask";
	}
	
	@GetMapping(value = "askForm")
	public String askFormPage() {
		return "yhs/askForm";
	}
	
	/*
	 * @GetMapping(value = "detailAsk") public String detailAsk(board_admin
	 * board_admin1 , Model model) {
	 * System.out.println("EmpController Start detailEmp..." ); // 1. EmpService안에
	 * detailEmp method 선언 // 1) parameter : empno // 2) Return Emp // // 2. EmpDao
	 * detailEmp method 선언 //// mapper ID , Parameter // emp =
	 * session.selectOne("tkEmpSelOne", empno); // System.out.println("emp->"+emp1);
	 * Emp emp = es.detailEmp(emp1.getEmpno()); model.addAttribute("emp",emp);
	 * 
	 * return "detailEmp"; }
	 */
	
}

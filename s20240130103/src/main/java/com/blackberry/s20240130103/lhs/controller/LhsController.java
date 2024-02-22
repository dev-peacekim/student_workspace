package com.blackberry.s20240130103.lhs.controller;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LhsController {
	
	private final JavaMailSender mailSender;
	
	@GetMapping(value = "loginForm")
	public String loginForm() {
		return "lhs/loginForm";
	}
	
	@GetMapping(value = "jointerms")
	public String joinTerms() {
		return "lhs/jointerms";
	}
	
	@GetMapping(value = "joinEmailForm")
	public String joinEmail() {
		return "lhs/joinemail";
	}
	
	@GetMapping(value = "joinForm")
	public String joinForm() {
		return "lhs/joinForm";
	}
	
	@ResponseBody
	@PostMapping(value = "emailChkAjax")
	public int emailChkAjax(@RequestParam(name = "email")String email) {
		StringBuilder sb = new StringBuilder();
		int chkNum = (int)(Math.random()*9999)+1;
		sb.append("안녕하세요 반갑습니다.\n");
		sb.append("인증번호 : "+chkNum);
		System.out.println(sb.toString());
		String tomail = email;
		String setFrom = "dlgkstnrn@gmail.com";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
			messageHelper.setFrom(setFrom);
			messageHelper.setTo(tomail);
			messageHelper.setSubject("인증번호");
			messageHelper.setText(sb.toString());
			mailSender.send(message);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return chkNum;
	}
	
}

package com.blackberry.s20240130103.lhs.controller;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blackberry.s20240130103.lhs.domain.User;
import com.blackberry.s20240130103.lhs.service.UserService;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LhsController {
	
	private final JavaMailSender mailSender;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserService userService;
	
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
	public String joinForm(@RequestParam(name = "email") String email,Model model) {
//		public String joinForm(Model model) {
		model.addAttribute("email",email);
		return "lhs/joinForm";
	}
	
	@ResponseBody
	@PostMapping(value = "emailChkAjax")
	public int emailChkAjax(@RequestParam(name = "email")String email) {
		StringBuilder sb = new StringBuilder();
		int chkNum = (int)(Math.random()*9000)+1000;
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
	
	@PostMapping(value = "userJoin")
	public String userJoin(HttpServletRequest request) {
		User user = new User();
		user.setUser_id(request.getParameter("user_id"));
		user.setUser_pw(bCryptPasswordEncoder.encode(request.getParameter("user_pw")));
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_email(request.getParameter("user_email"));
		user.setUser_nic(request.getParameter("user_nic"));;
		user.setUser_phone(request.getParameter("user_phone"));
		user.setUser_delete_chk(0);
		user.setUser_profile("임시파일이름");
		userService.joinUser(user);
		System.out.println("조인성공");
		return "loginForm";
	}
	
	@ResponseBody
	@PostMapping(value = "joinIdChk")
	public int joinIdChk(@RequestParam(name = "id")String id) {
		int result = userService.findUserById(id);
		return result;
	}
	
}

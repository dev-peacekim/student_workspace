package com.blackberry.s20240130103.lhs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blackberry.s20240130103.lhs.domain.User;
import com.blackberry.s20240130103.lhs.service.UserService;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LhsController {
	
	private final JavaMailSender mailSender;
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
	public String userJoin(HttpServletRequest request) throws IOException {
		FileOutputStream outputStream = null;
		String savedName = null;
		User user = new User();
		user.setUser_id(request.getParameter("user_id"));
		user.setUser_pw(request.getParameter("user_pw"));
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_email(request.getParameter("user_email"));
		user.setUser_nic(request.getParameter("user_nic"));;
		user.setUser_phone(request.getParameter("user_phone"));
		user.setUser_delete_chk(0);
		//파일 업로드 부분
		try {
			Part image = request.getPart("user_profile");
			InputStream is = image.getInputStream();
			String suffix = image.getSubmittedFileName().split("\\.")[1];
			String uploadPath = request.getSession().getServletContext().getRealPath("/upload/userImg/");
			File fileDirectory = new File(uploadPath);
			if(!fileDirectory.exists()) {
				fileDirectory.mkdirs();
			}
			savedName = user.getUser_id() + "." + suffix;
			File imgFile = new File(uploadPath+savedName);
			outputStream = new FileOutputStream(imgFile);
			int read;
			byte[] bytes = new byte[1024];
			while((read=is.read(bytes))!=-1) {
				outputStream.write(bytes,0,read);
			}
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}finally {
			outputStream.close();
		}
		user.setUser_profile(savedName);
		userService.joinUser(user);
		System.out.println("조인성공");
		return "lhs/loginForm";
	}
	
	@ResponseBody
	@PostMapping(value = "joinIdChk")
	public int joinIdChk(@RequestParam(name = "id")String id) {
		int result = userService.joinIdChk(id);
		return result;
	}
	
	@PostMapping("userLogin")
	public String userLogin(User user,HttpServletRequest request) {
		int result = userService.loginChk(user);
		if(result==1) {
			request.getSession().invalidate();
			HttpSession session = request.getSession(true);
			session.setAttribute("user_no", user.getUser_no());
			session.setAttribute("user_name", user.getUser_name());
			session.setAttribute("user_profile", user.getUser_profile());
			System.out.println("LHSController session user no : " + session.getAttribute("user_no"));
			return "redirect:/main";
		}else {
			request.setAttribute("islogin", 0);
			return "lhs/loginForm";
		}
	}
	
	@GetMapping("userLogout")
	public String userLogout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "thymeleaf/index";
	}
	
	@GetMapping("myPage")
	public String userProfile(HttpServletRequest request,Model model) {
		String userNo = request.getSession().getAttribute("user_no").toString();
		User user = userService.findUserByNo(userNo);
		System.out.println(user);
		model.addAttribute("user", user);
		return "lhs/userMypage";
	}
}

package com.blackberry.s20240130103.lhs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blackberry.s20240130103.lhs.model.BoardAdmin;
import com.blackberry.s20240130103.lhs.model.BoardComm;
import com.blackberry.s20240130103.lhs.model.User;
import com.blackberry.s20240130103.lhs.service.AdminService;
import com.blackberry.s20240130103.lhs.service.LhsPaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LhsAdminController {
	
	private final AdminService adminService;
	
	@GetMapping("/adminMain")
	public String adminMain(Model model) {
		Map<String, Long> tableCntMap = adminService.selectTablesCnt();
		List<User> deleteRequestUserList = adminService.selectUsersDeleteRequest();
		List<BoardAdmin> boardAdminList = adminService.selectBoardAdminList();
		model.addAttribute("cntMap",tableCntMap);
		model.addAttribute("deleteUserList", deleteRequestUserList);
		model.addAttribute("boardAdminList", boardAdminList);
		return "admin/admin_main";
	}
	
	@GetMapping("userCntListAjax")
	@ResponseBody
	public String listMapUserCnt(){
		System.out.println("여기옴");
		List<Map<String, Long>> userJoinCntList = adminService.selectUserJoinCnt();
		Gson gson = new GsonBuilder().setDateFormat("MM-dd").create();
		String jsonstr = gson.toJson(userJoinCntList);
		System.out.println(jsonstr);
		return jsonstr;
	}
	
	@GetMapping("admin_boardList")
	public String adminBoardList(BoardComm board,Model model) {
		System.out.println(board);
		int cboardCnt = adminService.selectBoardCommCnt(board);
		LhsPaging paging = new LhsPaging(cboardCnt, board.getCurrentPage());
		board.setStart(paging.getStart());
		board.setEnd(paging.getEnd());
		List<BoardComm> cboardList = adminService.selectBoardCommList(board);
		model.addAttribute("paging", paging);
		model.addAttribute("boardList", cboardList);
		model.addAttribute("searchkind", board.getSearchkind());
		model.addAttribute("searchValue", board.getSearchValue());
		return "admin/admin_boardList";
	}
	
}

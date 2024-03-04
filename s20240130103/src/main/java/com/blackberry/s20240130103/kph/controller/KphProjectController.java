package com.blackberry.s20240130103.kph.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphProjectTask;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.kph.service.KphPaging;
import com.blackberry.s20240130103.kph.service.KphProjectService;
import com.blackberry.s20240130103.lhs.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
public class KphProjectController {
	
	private final KphProjectService kphProjectService;

	@GetMapping("mainLogic")
	public String mainLogic(HttpServletRequest request, Model model) {
		System.out.println("KphProjectController mainLogic start...");
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		
		// 프로젝트 리스트 + 과업 설정
		List<KphProject> projectList = kphProjectService.projectList(user_no);
		Iterator<KphProject> projectIter = projectList.iterator();
		
		int totalCompTaskCount = 0;
		int totalUnCompTaskCount = 0;
		
		while(projectIter.hasNext()) {
			KphProject kphProject = projectIter.next(); 
			kphProject.setUser_no(user_no);
			List<KphTask> unComptaskList = kphProjectService.unCompTaskListByProjectNo(kphProject.getProject_no());
			List<KphTask> compTaskList = kphProjectService.compTaskListByProjectNo(kphProject.getProject_no());
			int isEvalByUser = kphProjectService.isEvalByUser(kphProject);
			kphProject.setUncomp_task_count(unComptaskList.size());
			kphProject.setComp_task_count(compTaskList.size());
			kphProject.setIsEvalByUser(isEvalByUser);
			totalCompTaskCount += compTaskList.size();
			totalUnCompTaskCount += unComptaskList.size();
		}
		
		int totalTaskCount = totalCompTaskCount+totalUnCompTaskCount;
		
		// 주소록 유저 리스트
		List<KphUsers> addressUserList = kphProjectService.addressUserList(user_no);
		
		System.out.println("KphProjectController mainLogic projectList size=>" + projectList.size());
		System.out.println("KphProjectController mainLogic addressUserList size=>" + addressUserList.size());
		
		model.addAttribute("projectList", projectList);
		model.addAttribute("totalCompTaskCount", totalCompTaskCount);
		model.addAttribute("totalUnCompTaskCount", totalUnCompTaskCount);
		model.addAttribute("totalTaskCount", totalTaskCount);
		model.addAttribute("addressUserList", addressUserList);
		return "main";
	}
	
	
	@GetMapping("projectAddForm")
	public String projectAddForm() {
		return "kph/projectAddForm";
	}
	
	
	@PostMapping("projectAdd")
	public String projectAdd(KphProject project, HttpServletRequest request) {
		System.out.println("KphProjectController projectAdd start...");
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		
		project.setUser_no(user_no);
				
		int result = kphProjectService.projectAdd(project);
		System.out.println("KphProjectController projectAdd result=>" + result);
		return "redirect:/main";
	}
	
	@PostMapping("evalForm")
	public String evalForm(HttpServletRequest request, Model model) {
		System.out.println("KphProjectController evalForm start...");
		
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		Long project_no = Long.parseLong(request.getParameter("project_no"));
		KphUserProject kphUserProject = new KphUserProject();
		kphUserProject.setProject_no(project_no);
		kphUserProject.setUser_no(user_no);
		
		// 본인 제외 나머지 프로젝트 참여 인원
		List<KphUsers> userList = kphProjectService.userListByProjectNoExceptOwn(kphUserProject);
		
		System.out.println("KphProjectController evalForm userList size=>" + userList.size());
		model.addAttribute("userList", userList);
		model.addAttribute("userListSize", userList.size());
		model.addAttribute("project_no", project_no);
		
		return "kph/evalForm";
	}
	
	@PostMapping("eval")
	public String eval(HttpServletRequest request) {
		System.out.println("KphProjectController eval start...");
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		Long project_no = Long.parseLong(request.getParameter("project_no"));
		
		int userListSize = Integer.parseInt(request.getParameter("userListSize"));
		for (int i=0; i<userListSize; i++) {
			Long puser_no = Long.parseLong(request.getParameter("user" + i));
			int eval_score = Integer.parseInt(request.getParameter("user" + i +"_score"));
			KphEval eval = new KphEval();
			eval.setUser_no(user_no);
			eval.setProject_no(project_no);
			eval.setPuser(puser_no);
			eval.setEval_score(eval_score);
			int result = kphProjectService.eval(eval);
			System.out.println("KphProjectController eval user" + i + " eval result =>" + result);
		}
		
		return "redirect:/main";
	}
	
	@PostMapping("projectAddressSearch")
	@ResponseBody
	public List<KphUsers> projectAddressSearch(@RequestBody KphUsers user, HttpServletRequest request) {
		
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		user.setUser_no(user_no);
		System.out.println(user);
		
		List<KphUsers> userList = null;
		
		if (user.getUser_name() == "") {
			userList = kphProjectService.addressUserList(user_no);
			System.out.println(userList);
		} else {
			userList = kphProjectService.addressUserListByName(user);
			System.out.println(userList);
		}
		
		return userList;
	}
	
	@GetMapping("totalTaskList")
	public String totalTaskList(KphProjectTask kphProjectTask, HttpServletRequest request, Model model) {
		
		System.out.println("KphProjectController totalTaskList start...");
		
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		int totalTaskCount = kphProjectService.totalTaskCountByUserNo(user_no);
		System.out.println("KphProjectController totalTaskList totalTaskCount=>" + totalTaskCount);
		
		KphPaging kphPaging = new KphPaging(totalTaskCount, kphProjectTask.getCurrentPage());
		
		kphProjectTask.setStart(kphPaging.getStart());
		kphProjectTask.setEnd(kphPaging.getEnd());
		kphProjectTask.setUser_no(user_no);
		
		List<KphProjectTask> totalProjectTaskList = kphProjectService.totalTaskList(kphProjectTask);
		System.out.println("KphProjectController totalTaskList totalProjectTaskList.size=>"+ totalProjectTaskList.size());
		
		model.addAttribute("totalProjectTaskList", totalProjectTaskList);
		model.addAttribute("kphPaging", kphPaging);
		
		return "kph/totalTaskList";
	}
	
}

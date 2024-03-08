package com.blackberry.s20240130103.kph.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphProjectTask;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.kph.service.KphPaging;
import com.blackberry.s20240130103.kph.service.KphProjectService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class KphProjectController {
	
	private final KphProjectService kphProjectService;

	@GetMapping("mainLogic")
	public String mainLogic(HttpServletRequest request, Model model) {
		System.out.println("KphProjectController mainLogic start...");
		HttpSession session = request.getSession();
		Long user_no = (Long)session.getAttribute("user_no");
		
		Map<String, Object> mainLogic = kphProjectService.mainLogic(user_no);
		List<KphUsers> addressUserList = kphProjectService.addressUserList(user_no);
		
		model.addAttribute("projectList", mainLogic.get("projectList"));
		model.addAttribute("totalCompTaskCount", mainLogic.get("totalCompTaskCount"));
		model.addAttribute("totalUnCompTaskCount", mainLogic.get("totalUnCompTaskCount"));
		model.addAttribute("totalTaskCount", mainLogic.get("totalTaskCount"));
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
		kphProjectTask.setUser_no(user_no);
		
		int totaProjectlTaskCount = kphProjectService.totalProjectTaskCountByKeyword(kphProjectTask);
		System.out.println("KphProjectController totalTaskList totalTaskCount=>" + totaProjectlTaskCount);
		
		KphPaging kphPaging = new KphPaging(totaProjectlTaskCount, kphProjectTask.getCurrentPage());
		
		kphProjectTask.setStart(kphPaging.getStart());
		kphProjectTask.setEnd(kphPaging.getEnd());
		kphProjectTask.setUser_no(user_no);
		
		List<KphProjectTask> totalProjectTaskList = kphProjectService.totalProjectTaskList(kphProjectTask);
		System.out.println("KphProjectController totalTaskList totalProjectTaskList.size=>"+ totalProjectTaskList.size());
		
		model.addAttribute("totalProjectTaskList", totalProjectTaskList);
		model.addAttribute("kphPaging", kphPaging);
		model.addAttribute("keyword", kphProjectTask.getKeyword());
		model.addAttribute("searchFilter", kphProjectTask.getSearchFilter());
		model.addAttribute("sortFilter", kphProjectTask.getSortFilter());
		model.addAttribute("searchKeyword", kphProjectTask.getKeyword());
		model.addAttribute("clickedNav", kphProjectTask.getClickedNav());
		
		return "kph/totalTaskList";
	}
	
	@PostMapping("taskSearch")
	@ResponseBody
	public Map<String, Object> taskSearch(@RequestBody KphProjectTask kphProjectTask, HttpServletRequest request) {
		
		System.out.println("KphProjectController taskSearch start...");
		
		Long user_no = (Long)request.getSession().getAttribute("user_no");
		kphProjectTask.setUser_no(user_no);
		
		int totalTaskCount = kphProjectService.totalProjectTaskCountByKeyword(kphProjectTask);
		System.out.println("KphProjectController taskSearch totalTaskCount=>" + totalTaskCount);
		
		KphPaging kphPaging = new KphPaging(totalTaskCount, kphProjectTask.getCurrentPage());
		
		kphProjectTask.setStart(kphPaging.getStart());
		kphProjectTask.setEnd(kphPaging.getEnd());
		
		List<KphProjectTask> projectTaskList = kphProjectService.totalProjectTaskList(kphProjectTask);
		System.out.println("KphProjectController taskSearch projectTaskList.size=>"+ projectTaskList.size());
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("projectTaskList", projectTaskList);
		response.put("kphPaging", kphPaging);
				
		return response;
	}
	
	@PostMapping("detailProject")
	public String detailProject(KphTask kphTask, HttpServletRequest request, Model model) {
		
		System.out.println("KphProjectController detailProject start...");
		Map<String, Object> detailProject = kphProjectService.detailProject(kphTask); 
		
		int unCompTaskListCount = ((List<KphTask>)detailProject.get("unCompTaskList")).size();
		int compTaskListCount = ((List<KphTask>)detailProject.get("compTaskList")).size();
		
		model.addAttribute("taskList", detailProject.get("taskList"));
		model.addAttribute("projectMemberList", detailProject.get("projectMemberList"));
		model.addAttribute("unCompTaskListCount", unCompTaskListCount);
		model.addAttribute("compTaskListCount", compTaskListCount);
		
		return "kph/detailProject";
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("taskFilter")
	@ResponseBody
	public List<KphTask> taskFilter(@RequestBody Map<String, Object> requestMap) {
		
		System.out.println("KphProjectController taskFilter start...");
		
		String keyword = (String)requestMap.get("keyword");
		Long  project_no = Long.parseLong((String)requestMap.get("project_no"));
		
		System.out.println(keyword);
		System.out.println(project_no);
		
		List<KphTask> taskList = null;
		KphTask kphTask = new KphTask();
		kphTask.setProject_no(project_no);
		Map<String, Object> detailProject = kphProjectService.detailProject(kphTask); 
		
		if(keyword.equals("미완료 과업")) {
			taskList = (List<KphTask>)detailProject.get("unCompTaskList");
		} else if(keyword.equals("완료 과업")) {
			taskList = (List<KphTask>)detailProject.get("compTaskList");
		} else {
			taskList = (List<KphTask>)detailProject.get("taskList");
		}
		
		return taskList;
	}
	
	
}

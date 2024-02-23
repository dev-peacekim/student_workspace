package com.blackberry.s20240130103.kph.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.service.KphProjectService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class KphProjectController {
	
	private final KphProjectService kphProjectService;

	@GetMapping("mainLogic")
	public String mainLogic(HttpServletRequest request, Model model) {
		System.out.println("KphProjectController mainLogic start...");
		HttpSession session = request.getSession();
		int user_no = ((Long)session.getAttribute("user_no")).intValue();
		List<KphProject> projectList = kphProjectService.projectList(user_no);
		Iterator<KphProject> projectIter = projectList.iterator();
		
		int totalCompTaskCount = 0;
		int totalUnCompTaskCount = 0;
		
		while(projectIter.hasNext()) {
			KphProject kphProject = projectIter.next(); 
			List<KphTask> unComptaskList = kphProjectService.unCompTaskListByProjectNo(kphProject.getProject_no());
			List<KphTask> compTaskList = kphProjectService.compTaskListByProjectNo(kphProject.getProject_no());
			kphProject.setUncomp_task_count(unComptaskList.size());
			kphProject.setComp_task_count(compTaskList.size());
			totalCompTaskCount += compTaskList.size();
			totalUnCompTaskCount += unComptaskList.size();
		}
		
		int totalTaskCount = totalCompTaskCount+totalUnCompTaskCount;
		
		System.out.println("KphProjectController mainLogic projectList size=>" + projectList.size());
		model.addAttribute("projectList", projectList);
		model.addAttribute("totalCompTaskCount", totalCompTaskCount);
		model.addAttribute("totalUnCompTaskCount", totalUnCompTaskCount);
		model.addAttribute("totalTaskCount", totalTaskCount);
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
		int user_no = ((Long)session.getAttribute("user_no")).intValue();
		
		project.setUser_no(user_no);
				
		int result = kphProjectService.projectAdd(project);
		System.out.println("KphProjectController projectAdd result=>" + result);
		return "redirect:/main";
	}
	
}

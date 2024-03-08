package com.blackberry.s20240130103.kph.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.kph.dao.KphProjectDao;
import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphProjectTask;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUsers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KphProjectServiceImp implements KphProjectService {

	private final KphProjectDao kphProjectDao;
	
	@Override
	public Map<String, Object> mainLogic(Long user_no) {
		System.out.println("KphProjectServiceImp mainLogic start...");
		Map<String, Object> mainLogic = new HashMap<String, Object>();
		
		// 프로젝트 리스트 + 과업 설정
		List<KphProject> projectList = kphProjectDao.projectList(user_no);
		Iterator<KphProject> projectIter = projectList.iterator();
		
		int totalCompTaskCount = 0;
		int totalUnCompTaskCount = 0;
		
		while(projectIter.hasNext()) {
			KphProject kphProject = projectIter.next(); 
			kphProject.setUser_no(user_no);
			List<KphTask> unComptaskList = kphProjectDao.unCompTaskListByProjectNo(kphProject.getProject_no());
			List<KphTask> compTaskList = kphProjectDao.compTaskListByProjectNo(kphProject.getProject_no());
			int isEvalByUser = kphProjectDao.isEvalByUser(kphProject);
			kphProject.setUncomp_task_count(unComptaskList.size());
			kphProject.setComp_task_count(compTaskList.size());
			kphProject.setIsEvalByUser(isEvalByUser);
			totalCompTaskCount += compTaskList.size();
			totalUnCompTaskCount += unComptaskList.size();
		}
		
		int totalTaskCount = totalCompTaskCount+totalUnCompTaskCount;
		
		mainLogic.put("projectList", projectList);
		mainLogic.put("totalCompTaskCount", totalCompTaskCount);
		mainLogic.put("totalUnCompTaskCount", totalUnCompTaskCount);
		mainLogic.put("totalTaskCount", totalTaskCount);
		
		return mainLogic;
	}

	@Override
	public int projectAdd(KphProject project) {
		System.out.println("KphProjectServiceImp projectAdd start...");
		int result = kphProjectDao.projectAdd(project);
		System.out.println("KphProjectServiceImp projectAdd result => " + result);
		return result;
	}

	@Override
	public List<KphUsers> userListByProjectNoExceptOwn(KphUserProject kphUserProject) {
		System.out.println("KphProjectServiceImp userListByProjectNo start...");
		List<KphUsers> userList = kphProjectDao.userListByProjectNoExceptOwn(kphUserProject);
		return userList;
	}


	@Override
	public int eval(KphEval eval) {
		System.out.println("KphProjectServiceImp eval start...");
		int result = kphProjectDao.eval(eval);
		return result;
	}


	@Override
	public List<KphUsers> addressUserList(Long user_no) {
		System.out.println("KphProjectServiceImp addressUserList start...");
		List<KphUsers> addressUserList = kphProjectDao.addressUserList(user_no);
		return addressUserList;
	}


	@Override
	public List<KphUsers> addressUserListByName(KphUsers user) {
		System.out.println("KphProjectServiceImp addressUserListByName start...");
		List<KphUsers> addressUserList = kphProjectDao.addressUserListByName(user);
		return addressUserList;
	}


	@Override
	public List<KphProjectTask> totalProjectTaskList(KphProjectTask kphProjectTask) {
		System.out.println("KphProjectServiceImp totalTaskList start...");
		List<KphProjectTask> totalProjectTaskList = kphProjectDao.totalProjectTaskList(kphProjectTask);
		return totalProjectTaskList;
	}


	@Override
	public int totalProjectTaskCountByKeyword(KphProjectTask kphProjectTask) {
		System.out.println("KphProjectServiceImp totalTaskCountByKeyword start...");
		int totalTaskCount = kphProjectDao.totalProjectTaskCountByKeyword(kphProjectTask);
		return totalTaskCount;
	}


	@Override
	public Map<String, Object> detailProject(KphTask kphTask) {
		System.out.println("KphProjectServiceImp detailProject start...");
		Map<String, Object> detailProject = new HashMap<String, Object>();
		
		// 과업 리스트 세팅
		List<KphTask> taskList = kphProjectDao.taskListIncludingUsers(kphTask);
		detailProject.put("taskList", taskList);
		
		// 완료/미완료 과업 세팅
		List<KphTask> unCompTaskList = new ArrayList<KphTask>();
		List<KphTask> compTaskList = new ArrayList<KphTask>();
		Iterator<KphTask> taskListIt = taskList.iterator();
		
		while (taskListIt.hasNext()) {
			KphTask task = taskListIt.next();
			if(task.getTask_comp_chk() == 0) {
				unCompTaskList.add(task);
			} else {
				compTaskList.add(task);
			}
		}
		
		detailProject.put("unCompTaskList", unCompTaskList);
		detailProject.put("compTaskList", compTaskList);
		
		// 프로젝트 멤버 세팅
		List<KphUsers> projectMemberList = kphProjectDao.projectMemberList(kphTask);
		detailProject.put("projectMemberList", projectMemberList);
		
		return detailProject;
	}
	
}

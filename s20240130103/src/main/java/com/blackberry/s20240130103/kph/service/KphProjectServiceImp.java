package com.blackberry.s20240130103.kph.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blackberry.s20240130103.kph.dao.KphProjectDao;
import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUsers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KphProjectServiceImp implements KphProjectService {

	private final KphProjectDao kphProjectDao;

	@Override
	public int projectAdd(KphProject project) {
		System.out.println("KphProjectServiceImp projectAdd start...");
		int result = kphProjectDao.projectAdd(project);
		System.out.println("KphProjectServiceImp projectAdd result => " + result);
		return result;
	}


	@Override
	public List<KphProject> projectList(Long user_no) {
		System.out.println("KphProjectServiceImp projectList start...");
		List<KphProject> projectList = kphProjectDao.projectList(user_no);
		System.out.println("KphProjectServiceImp projectList projectList.size=>" + projectList.size());
		return projectList;
	}


	@Override
	public List<KphTask> unCompTaskListByProjectNo(Long project_no) {
		List<KphTask> upCompTaskList = kphProjectDao.unCompTaskListByProjectNo(project_no);
		return upCompTaskList;
	}


	@Override
	public List<KphTask> compTaskListByProjectNo(Long project_no) {
		List<KphTask> CompTaskList = kphProjectDao.compTaskListByProjectNo(project_no);
		return CompTaskList;
	}


	@Override
	public int isEvalByUser(KphProject kphProject) {
		int isEvalByUser = kphProjectDao.isEvalByUser(kphProject);
		return isEvalByUser;
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
	
}

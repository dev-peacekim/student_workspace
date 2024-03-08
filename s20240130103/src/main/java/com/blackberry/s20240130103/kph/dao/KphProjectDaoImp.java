package com.blackberry.s20240130103.kph.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphProjectTask;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUsers;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KphProjectDaoImp implements KphProjectDao {

	private final SqlSession session;

	@Override
	public int projectAdd(KphProject project) {
		System.out.println("KphProjectDaoImp projectAdd start...");
		session.insert("kphProjectInsertProc", project);
		return 1;
	}

	@Override
	public List<KphProject> projectList(Long user_no) {
		System.out.println("KphProjectDaoImp projectList start...");
		return session.selectList("kphProjectListByUserNo", user_no);
	}

	@Override
	public List<KphTask> unCompTaskListByProjectNo(Long project_no) {
		return session.selectList("kphUnCompTaskListByProjectNo", project_no);
	}

	@Override
	public List<KphTask> compTaskListByProjectNo(Long project_no) {
		return session.selectList("kphCompTaskListByProjectNo", project_no);
	}

	@Override
	public int isEvalByUser(KphProject kphProject) {
		return session.selectOne("kphProjectIsEvalByUser", kphProject);
	}

	@Override
	public List<KphUsers> userListByProjectNoExceptOwn(KphUserProject kphUserProject) {
		System.out.println("KphProjectDaoImp userListByProjectNo start...");
		List<KphUsers> userList = session.selectList("kphUserListByProjectNoExceptOwn", kphUserProject);
		return userList;
	}

	@Override
	public int eval(KphEval eval) {
		System.out.println("KphProjectDaoImp eval start...");
		int result = session.update("kphEvalInsert", eval);
		return result;
	}

	@Override
	public List<KphUsers> addressUserList(Long user_no) {
		System.out.println("KphProjectDaoImp addressUserList start...");
		return session.selectList("kphAddressUserList", user_no);
	}

	@Override
	public List<KphUsers> addressUserListByName(KphUsers user) {
		System.out.println("KphProjectDaoImp addressUserListByName start...");
		return session.selectList("kphAddressUserListByName", user);
	}

	@Override
	public List<KphProjectTask> totalProjectTaskList(KphProjectTask kphProjectTask) {
		System.out.println("KphProjectDaoImp addressUserListByName start...");
		return session.selectList("kphTotalTaskList", kphProjectTask);
	}

	@Override
	public int totalProjectTaskCountByKeyword(KphProjectTask kphProjectTask) {
		System.out.println("KphProjectDaoImp totalTaskCountByKeyword start...");
		return session.selectOne("kphTotalTaskCountByKeyword", kphProjectTask);
	}

	@Override
	public List<KphTask> taskListIncludingUsers(KphTask kphTask) {
		System.out.println("KphProjectDaoImp taskListIncludingUsers start...");
		
		List<KphTask> taskList = session.selectList("kphTaskListByProjectNo", kphTask);
		Iterator<KphTask> taskIt = taskList.iterator();
		
		while (taskIt.hasNext()) {
			KphTask task = taskIt.next();
			task.setUsers(session.selectList("kphUserListInTask", task));
		}
		
		return taskList;
	}

	@Override
	public List<KphUsers> projectMemberList(KphTask kphTask) {
		System.out.println("KphProjectDaoImp userListInProject start...");
		return session.selectList("kphUserListInProject", kphTask);
	}
	
}

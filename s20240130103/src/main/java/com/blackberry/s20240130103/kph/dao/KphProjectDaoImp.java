package com.blackberry.s20240130103.kph.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphProjectTask;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUserTask;
import com.blackberry.s20240130103.kph.model.KphUsers;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KphProjectDaoImp implements KphProjectDao {

	private final SqlSession session;
	private final PlatformTransactionManager transactionManager;

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
	public int isUserInProject(KphUserProject kphUserProject) {
		return session.selectOne("kphIsUserInProject", kphUserProject);
	}
	
	@Override
	public Long projectLeaderNo(Long project_no) {
		return session.selectOne("kphProjectLeaderNo", project_no);
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
	public List<KphUsers> projectMemberList(Long project_no) {
		System.out.println("KphProjectDaoImp userListInProject start...");
		return session.selectList("kphUserListByProjectNo", project_no);
	}

	@Override
	public int taskAdd(List<Long> userNoList, KphTask kphTask) {
		System.out.println("KphProjectDaoImp taskAdd start...");
		
		int result = 0;
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			System.out.println("KphProjectDaoImp taskAdd kphTask=> " + kphTask);
			session.insert("kphTaskInsertProc", kphTask);
			System.out.println("KphProjectDaoImp taskAdd insertTaskNo=> " + kphTask.getPo_task_no());
			
			for (int i = 0; i < userNoList.size(); i++) {
				KphUserTask kphUserTask = new KphUserTask();
				kphUserTask.setProject_no(kphTask.getProject_no());
				kphUserTask.setTask_no(kphTask.getPo_task_no());
				kphUserTask.setUser_no(userNoList.get(i));
				int userTaskInsertResult = session.insert("kphUserTaskInsert", kphUserTask);
				System.out.println("KphProjectDaoImp taskAdd insertTaskNo=> " + userTaskInsertResult);
			}
			
			result = 1;
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
			transactionManager.rollback(txStatus);
		}
		
		return result;
	}

	@Override
	public List<KphUsers> addressUserListExceptProjectMember(KphUserProject kphUserProject) {
		System.out.println("KphProjectDaoImp addressUserListExceptProjectMember start...");
		return session.selectList("kphAddressUserListExceptProjectMember", kphUserProject);
	}

	@Override
	public int projectMemberAdd(KphUserProject kphUserProject) {
		System.out.println("KphProjectDaoImp projectMemberAdd start...");
		return session.insert("kphProjectMemberAdd", kphUserProject);
	}

	@Override
	public KphProject getProjectByProjectNo(KphProject kphProject) {
		System.out.println("KphProjectDaoImp getProjectByProjectNo start...");
		return session.selectOne("kphGetProjectByProjectNo", kphProject);
	}

	@Override
	public int projectUpdate(KphProject kphProject) {
		System.out.println("KphProjectDaoImp projectUpdate start...");
		return session.update("kphProjectUpdate", kphProject);
	}

	@Override
	public int projectDelete(KphProject kphProject) {
		System.out.println("KphProjectDaoImp projectDelete start...");
		return session.update("kphProjectDelete", kphProject);
	}

	@Override
	public int projectMemberDelete(KphUserProject kphUserProject) {
		System.out.println("KphProjectDaoImp projectMemberDelete start...");
		return session.delete("kphProjectMemberDelete", kphUserProject);
	}

}

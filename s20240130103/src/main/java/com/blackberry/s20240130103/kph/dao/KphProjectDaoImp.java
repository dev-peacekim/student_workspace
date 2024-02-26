package com.blackberry.s20240130103.kph.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphTask;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KphProjectDaoImp implements KphProjectDao {

	private final SqlSession session;
	private final PlatformTransactionManager transactionManager;

	@Override
	public int projectAdd(KphProject project) {
		System.out.println("KphProjectDaoImp projectAdd start...");
		
		int result = 0;
		TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			session.insert("kphProjectInsert", project);
			Long project_no = session.selectOne("kphMaxProjectNoByUserNo", project);
			project.setProject_no(project_no);
			result = session.insert("kphLeaderUserProjectInsert", project);
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			System.out.println(e.getMessage());
			result = -1;
		}
		
		return result;
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
	
}

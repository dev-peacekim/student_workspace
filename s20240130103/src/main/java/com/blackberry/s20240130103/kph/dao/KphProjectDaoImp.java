package com.blackberry.s20240130103.kph.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphTask;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KphProjectDaoImp implements KphProjectDao {

	private final SqlSession session;

	@Override
	public int projectAdd(KphProject project) {
		System.out.println("KphProjectDaoImp projectAdd start...");
		System.out.println(project);
		return session.insert("kphProjectInsert", project);
	}

	@Override
	public List<KphProject> projectList(int user_no) {
		System.out.println("KphProjectDaoImp projectList start...");
		return session.selectList("kphProjectList", user_no);
	}

	@Override
	public List<KphTask> unCompTaskListByProjectNo(int project_no) {
		System.out.println("KphProjectDaoImp unCompTaskListByProjectNo start...");
		return session.selectList("kphUnCompTaskListByProjectNo", project_no);
	}

	@Override
	public List<KphTask> compTaskListByProjectNo(int project_no) {
		System.out.println("KphProjectDaoImp compTaskListByProjectNo start...");
		return session.selectList("kphCompTaskListByProjectNo", project_no);
	}
	
}

package com.blackberry.s20240130103.kph.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.blackberry.s20240130103.kph.model.KphProject;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class KphProjectDaoImp implements KphProjectDao {

	private final SqlSession session;

	@Override
	public int projectAdd(KphProject project) {
		System.out.println("KphProjectDaoImp projectAdd start...");
		return session.insert("kphProjectInsert", project);
	}

	@Override
	public List<KphProject> projectList(int user_no) {
		System.out.println("KphProjectDaoImp projectList start...");
		return session.selectList("kphProjectList", user_no);
	}
	
}

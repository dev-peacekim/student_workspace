package com.blackberry.s20240130103.kph.dao;

import java.util.List;

import com.blackberry.s20240130103.kph.model.KphProject;

public interface KphProjectDao {

	int projectAdd(KphProject project);

	List<KphProject> projectList(int user_no);
	
}

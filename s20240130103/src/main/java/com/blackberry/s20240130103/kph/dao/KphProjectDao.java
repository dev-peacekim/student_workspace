package com.blackberry.s20240130103.kph.dao;

import java.util.List;

import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphTask;

public interface KphProjectDao {

	int projectAdd(KphProject project);

	List<KphProject> projectList(int user_no);

	List<KphTask> unCompTaskListByProjectNo(int project_no);

	List<KphTask> compTaskListByProjectNo(int project_no);
	
}

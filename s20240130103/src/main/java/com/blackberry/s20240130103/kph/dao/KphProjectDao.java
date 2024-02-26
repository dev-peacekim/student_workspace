package com.blackberry.s20240130103.kph.dao;

import java.util.List;

import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphTask;

public interface KphProjectDao {

	int projectAdd(KphProject project);

	List<KphProject> projectList(Long user_no);

	List<KphTask> unCompTaskListByProjectNo(Long project_no);

	List<KphTask> compTaskListByProjectNo(Long project_no);

	int isEvalByUser(KphProject kphProject);
	
}

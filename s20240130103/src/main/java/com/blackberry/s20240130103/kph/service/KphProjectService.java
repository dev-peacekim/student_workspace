package com.blackberry.s20240130103.kph.service;

import java.util.List;

import com.blackberry.s20240130103.kph.model.KphProject;

public interface KphProjectService {

	int projectAdd(KphProject project);

	List<KphProject> projectList(int user_no);

}

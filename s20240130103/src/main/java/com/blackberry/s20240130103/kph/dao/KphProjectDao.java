package com.blackberry.s20240130103.kph.dao;

import java.util.List;
import java.util.Map;

import com.blackberry.s20240130103.kph.model.KphEval;
import com.blackberry.s20240130103.kph.model.KphProject;
import com.blackberry.s20240130103.kph.model.KphProjectTask;
import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUserProject;
import com.blackberry.s20240130103.kph.model.KphUsers;

public interface KphProjectDao {

	int projectAdd(KphProject project);

	List<KphProject> projectList(Long user_no);

	List<KphTask> unCompTaskListByProjectNo(Long project_no);

	List<KphTask> compTaskListByProjectNo(Long project_no);

	int isEvalByUser(KphProject kphProject);

	List<KphUsers> userListByProjectNoExceptOwn(KphUserProject kphUserProject);

	int eval(KphEval eval);

	List<KphUsers> addressUserList(Long user_no);

	List<KphUsers> addressUserListByName(KphUsers user);

	List<KphProjectTask> totalProjectTaskList(KphProjectTask kphProjectTask);

	int totalProjectTaskCountByKeyword(KphProjectTask kphProjectTask);
	
	int isUserInProject(KphUserProject kphUserProject);

	List<KphTask> taskListIncludingUsers(KphTask kphTask);

	List<KphUsers> projectMemberList(Long project_no);

	int taskAdd(List<Long> userNoList, KphTask kphTask);

	Long projectLeaderNo(Long project_no);

	List<KphUsers> addressUserListExceptProjectMember(KphUserProject kphUserProject);

	int projectMemberAdd(KphUserProject kphUserProject);
	
}

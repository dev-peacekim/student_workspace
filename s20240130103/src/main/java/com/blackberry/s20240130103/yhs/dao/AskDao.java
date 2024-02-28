package com.blackberry.s20240130103.yhs.dao;

import java.util.List;

import com.blackberry.s20240130103.yhs.model.Ask;


public interface AskDao {

	int totalAsk();
	
	List<Ask> listAsk(Ask ask);

	int updateAsk(Ask ask);
	
	/*
	 * Emp detailEmp(int empno);
	 * 
	 * int updateEmp(Emp emp);
	 * 
	 * List<Emp> listManager();
	 * 
	 * int insertEmp(Emp emp);
	 * 
	 * List<Emp> empSearchList3(Emp emp);
	 * 
	 * int condTotalEmp(Emp emp);
	 * 
	 * int deleteEmp(int empno);
	 * 
	 * List<EmpDept> listEmpDept();
	 * 
	 * String deptName(int deptno);
	 */
	
}

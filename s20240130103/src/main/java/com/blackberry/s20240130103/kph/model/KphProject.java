package com.blackberry.s20240130103.kph.model;

import java.util.Date;

import lombok.Data;

@Data
public class KphProject {

	private int project_no;
	private String project_title;
	private String project_content;
	private String project_start;
	private String project_end;
	private int user_no;
	private String project_date;
	private int project_delete_chk;
	private int project_comp_chk;
	
	// 조회용 컬럼
	private int comp_task_count; // 완료된 과업 갯수
	private int uncomp_task_count; // 미완료된 과업 갯수
	
}

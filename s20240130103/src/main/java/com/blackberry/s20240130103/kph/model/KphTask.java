package com.blackberry.s20240130103.kph.model;

import java.util.Date;

import lombok.Data;

@Data
public class KphTask {
	
	private int project_no;
	private int task_no;
	private String task_title;
	private Date task_start;
	private Date task_end;
	private int task_comp_chk;
	
}

package com.blackberry.s20240130103.kph.model;

import java.util.Date;

import lombok.Data;

@Data
public class KphProject {

	private int project_no;
	private String project_title;
	private String project_content;
	private Date project_start;
	private Date project_end;
	private int user_no;
	private Date project_date;
	private int project_delete_chk;
	private int project_comp_chk;
	
}

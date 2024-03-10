package com.blackberry.s20240130103.kph.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class KphTask {
	
	private Long project_no;
	private Long task_no;
	private String task_title;
	private String task_start;
	private String task_end;
	private int task_comp_chk;
	
	// 조회용
	private List<KphUsers> users;
	
	// insert용
	private LocalDateTime task_end2;
	
}

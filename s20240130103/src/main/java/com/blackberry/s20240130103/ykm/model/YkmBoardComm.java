package com.blackberry.s20240130103.ykm.model;

import lombok.Data;

@Data
public class YkmBoardComm {

	// BoardComm DTO
	private int cboard_no;
	private String cboard_title;
	private String cboard_content;
	private String cboard_date;
	private Long user_no;
	private int cboard_delete_chk;
	private int comm_big;
	private int comm_mid;
	private int comm_big2;
	private int comm_mid2;
	
	
	// BoardCommFile DTO
	private int cboard_cnt;
	private String cboard_name;
}
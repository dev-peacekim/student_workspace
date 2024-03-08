package com.blackberry.s20240130103.ykm.model;

import java.util.Date;

import lombok.Data;

@Data
public class YkmBoardComm {

	// BoardComm
	private int cboard_no;
	private String cboard_title;
	private String cboard_content;
	private int cboard_viewcnt;
	private Date cboard_date;
	private Long user_no;
	private int cboard_delete_chk;
	private int comm_big;
	private int comm_mid;
	private int comm_big2;
	private int comm_mid2;
	private Date comm_update_date;
	
	// BoardCommFile
	private int cboard_file_cnt;
	private String cboard_name;
	
	// Users 조회용
	private String user_id;
	private String user_nic;
//	private String user_name;
//	private String user_email;
//	private String user_phone;
	private String user_profile;

	//
	private int countComment;
	private int reply_count;
}
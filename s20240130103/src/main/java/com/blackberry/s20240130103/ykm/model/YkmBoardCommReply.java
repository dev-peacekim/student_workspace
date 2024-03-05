package com.blackberry.s20240130103.ykm.model;

import java.util.Date;

import lombok.Data;

@Data
public class YkmBoardCommReply {
	
	// ReplyVO
	private int creply_no;
	private String creply_content;
	private Date creply_date;
	private int creply_group;
	private int creply_level;
	private int creply_indent;
	private int cboard_no;
	private Long user_no;
	private int creply_delete_chk;
	private Date comm_update_date;

	
	// Users 조회용
	private String user_id;
	private String user_profile;
	
}

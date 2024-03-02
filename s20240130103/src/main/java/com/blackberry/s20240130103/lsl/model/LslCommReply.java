package com.blackberry.s20240130103.lsl.model;

import java.util.Date;

import lombok.Data;

@Data
public class LslCommReply {
	// 댓글 번호 Seq
	private Long creply_no;
	private String creply_content;
	//private String creply_date;
	private Date creply_date;
	private int creply_group;
	private int creply_level;
	private int creply_indent;
	
	
	
	
	// 글번호 FK
	private int cboard_no;
	
	// 작성자 번호 FK
	private int user_no;
	private String USER_NIC; 
	private String USER_PROFILE;
	private int creply_delete_chk;


}

package com.blackberry.s20240130103.ykm.model;

import lombok.Data;

@Data
public class YkmBoardCommReply {
	
	private int creply_no;
	private String creply_content;
	private String creply_date;
	private int creply_group;
	private int creply_indent;
	private int cboard_no;
	private Long user_no;
	private int creply_delete_chk;
	
}

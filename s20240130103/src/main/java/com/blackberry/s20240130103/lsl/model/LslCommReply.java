package com.blackberry.s20240130103.lsl.model;

import java.util.Date;

public class LslCommReply {
	// 댓글 번호 Seq
	private int creply_no;
	private String creply_coment;
	private Date creply_date;
	private int creply_group;
	private int creply_level;
	private int creply_indent;
	// 글번호 FK
	private int cboard_no;
	// 작성자 번호 FK
	private int user_no;
	private int creply_delete_chk;


}

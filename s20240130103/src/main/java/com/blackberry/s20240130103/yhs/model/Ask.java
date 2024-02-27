package com.blackberry.s20240130103.yhs.model;

import java.util.Date;

import lombok.Data;

@Data
public class Ask {
	private long user_no;
	private long col;
	private String admin_date;
	private String admin_title;
	private String admin_content;
	private String admin_start;
	private long admin_reply_group;
	private long admin_reply_chk;
}

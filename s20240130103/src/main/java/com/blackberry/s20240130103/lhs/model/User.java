package com.blackberry.s20240130103.lhs.model;


import lombok.Data;

@Data
public class User {
	private Long user_no;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_nic;
	private String user_email;
	private String user_phone;
	private String user_profile;
	private int user_delete_chk;
	private String user_date;
	
}

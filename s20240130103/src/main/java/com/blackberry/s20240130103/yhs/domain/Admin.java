package com.blackberry.s20240130103.yhs.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "BOARD_ADMIN")
@Getter
@Setter
@ToString
public class Admin {
	@Id
	private long user_no;
	private long col;
	@Column(nullable = false, columnDefinition = "date default sysdate")
	private Date admin_date = new Date();
	private String admin_title;
	private String admin_content;
	private Date admin_start = new Date();
	private long admin_reply_group;
	private long admin_reply_chk;
		
}

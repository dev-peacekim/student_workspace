package com.blackberry.s20240130103.lhs.domain;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "users")
@Entity
@Getter
@Setter
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq",initialValue = 1,allocationSize = 1)
@ToString
@DynamicInsert
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	private Long user_no;
	@Column(nullable = false)
	private String user_id;
	@Column(nullable = false)
	private String user_pw;
	@Column(nullable = false)
	private String user_name;
	@Column(nullable = false)
	private String user_nic;
	@Column(nullable = false)
	private String user_email;
	@Column(nullable = false)
	private String user_phone;
	private String user_profile;
	@Column(nullable = false,columnDefinition = "default 0")
	private int user_delete_chk;
	@Column(nullable = false)
	private Date user_date;
	
}

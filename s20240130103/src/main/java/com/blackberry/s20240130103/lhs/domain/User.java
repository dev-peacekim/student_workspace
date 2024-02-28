package com.blackberry.s20240130103.lhs.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
	@Column(nullable = false)
	private int user_delete_chk;
	
	@Column(nullable = false,updatable = false)
	private LocalDateTime user_date;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime user_update_date;
	
}

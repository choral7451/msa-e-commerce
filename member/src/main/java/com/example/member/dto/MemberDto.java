package com.example.member.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberDto {
	private String email;
	private String name;
	private String pwd;
	private String memberId;
	private LocalDateTime createdAt;

	private String encryptedPwd;
}

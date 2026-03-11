package com.example.member.dto;

import com.example.member.jpa.MemberEntity;

public record MemberDto(
	 String email,
	 String name,
	 String pwd
) {

	public MemberEntity toMemberEntity(String memberId, String encryptedPwd) {
		return MemberEntity.create(email, name, memberId, encryptedPwd);
	}
}

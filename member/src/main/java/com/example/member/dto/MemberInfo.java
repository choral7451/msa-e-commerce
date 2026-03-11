package com.example.member.dto;

import com.example.member.jpa.MemberEntity;

public record MemberInfo(
	 String memberId,
	 String email,
	 String name
) {

	public static MemberInfo fromEntity(MemberEntity entity) {
		return new MemberInfo(entity.getMemberId(), entity.getEmail(), entity.getName());
	}
}

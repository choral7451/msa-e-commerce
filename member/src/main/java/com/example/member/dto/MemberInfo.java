package com.example.member.dto;

import java.util.List;

import com.example.member.jpa.MemberEntity;

public record MemberInfo(
	 String memberId,
	 String email,
	 String name,
	 List<OrderInfo> orders
) {

	public static MemberInfo fromEntity(MemberEntity entity) {
		return new MemberInfo(entity.getMemberId(), entity.getEmail(), entity.getName(), List.of());
	}
}

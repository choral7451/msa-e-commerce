package com.example.member.vo;

import java.util.List;

import com.example.member.dto.MemberInfo;

public record ResponseMember(
	String memberId,
	String email,
	String name,
	List<ResponseOrder> orders
) {
	public static ResponseMember fromInfo(MemberInfo info) {
		return new ResponseMember(info.memberId(), info.email(), info.name(), List.of());
	}
}



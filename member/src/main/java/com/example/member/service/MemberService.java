package com.example.member.service;

import java.util.List;

import com.example.member.dto.MemberDto;
import com.example.member.dto.MemberInfo;

public interface MemberService {
	MemberInfo createMember(MemberDto memberDto);
	MemberInfo getMemberByMemberId(String memberId);
	List<MemberInfo> getAllMembers();
}

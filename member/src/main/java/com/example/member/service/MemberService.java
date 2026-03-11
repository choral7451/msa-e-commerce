package com.example.member.service;

import com.example.member.dto.MemberDto;
import com.example.member.dto.MemberInfo;

public interface MemberService {
	MemberInfo createMember(MemberDto memberDto);
}

package com.example.member.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.member.dto.MemberDto;
import com.example.member.dto.MemberInfo;
import com.example.member.jpa.MemberEntity;
import com.example.member.jpa.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final BCryptPasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;

	@Override
	public MemberInfo createMember(MemberDto memberDto) {
		String memberId = UUID.randomUUID().toString();
		String encryptedPwd = passwordEncoder.encode(memberDto.pwd());

		MemberEntity member = memberRepository.save(memberDto.toMemberEntity(memberId, encryptedPwd));

		return MemberInfo.fromEntity(member);
	}
}

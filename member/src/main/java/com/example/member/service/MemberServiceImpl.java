package com.example.member.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.member.dto.MemberDto;
import com.example.member.jpa.MemberEntity;
import com.example.member.jpa.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	Environment env;

	private final MemberRepository memberRepository;

	@Override
	public MemberDto createMember(MemberDto memberDto) {
		memberDto.setMemberId(UUID.randomUUID().toString());

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		MemberEntity memberEntity = mapper.map(memberDto, MemberEntity.class);
		memberEntity.setEncryptedPwd("encrypted_password");

		memberRepository.save(memberEntity);

		return mapper.map(memberEntity, MemberDto.class);
	}
}

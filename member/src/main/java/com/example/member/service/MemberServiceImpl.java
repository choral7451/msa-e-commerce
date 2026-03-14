package com.example.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.member.dto.MemberDto;
import com.example.member.dto.MemberInfo;
import com.example.member.jpa.MemberEntity;
import com.example.member.jpa.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

	private final BCryptPasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;

	@Override
	public MemberInfo createMember(MemberDto memberDto) {
		String memberId = UUID.randomUUID().toString();
		String encryptedPwd = passwordEncoder.encode(memberDto.pwd());

		MemberEntity member = memberRepository.save(memberDto.toMemberEntity(memberId, encryptedPwd));

		return MemberInfo.fromEntity(member);
	}

	@Override
	public MemberInfo getMemberByMemberId(String memberId) {
		MemberEntity member = memberRepository.findByMemberId(memberId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

		return MemberInfo.fromEntity(member);
	}

	@Override
	public List<MemberInfo> getAllMembers() {
		return memberRepository.findAll().stream()
			.map(MemberInfo::fromEntity)
			.toList();
	}

	@Override
	public MemberInfo getMemberByEmail(String email) {
		MemberEntity member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

		return MemberInfo.fromEntity(member);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MemberEntity member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException(email));

		return new User(member.getEmail(), member.getEncryptedPwd(), new ArrayList<>());
	}
}

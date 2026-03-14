package com.example.member.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
	Optional<MemberEntity> findByMemberId(String memberId);
	Optional<MemberEntity> findByEmail(String email);
}

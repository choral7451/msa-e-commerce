package com.example.member.jpa;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "members")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50, unique = true)
	private String email;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, unique = true)
	private String memberId;

	@Column(nullable = false, unique = true)
	private String encryptedPwd;

	private MemberEntity(String email, String name, String memberId, String encryptedPwd) {
		this.email = email;
		this.name = name;
		this.memberId = memberId;
		this.encryptedPwd = encryptedPwd;
	}

	public static MemberEntity create(String email, String name, String memberId, String encryptedPwd) {
		return new MemberEntity(email, name, memberId, encryptedPwd);
	}
}

package com.example.member.vo;

import com.example.member.dto.MemberDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RequestMember {
	@NotNull(message = "Email cannot be null")
	@Size(min = 2, message = "Email not be less then two characters")
	private String email;

	@NotNull(message = "Password cannot be null")
	@Size(min = 8, message = "Password must be equal or grater then 8 characters")
	private String pwd;

	@NotNull(message = "Name cannot be null")
	@Size(min = 2, message = "Name not be less then two characters")
	private String name;

	public MemberDto toMemberDto() {
		return new MemberDto(email,name,pwd);
	}
}

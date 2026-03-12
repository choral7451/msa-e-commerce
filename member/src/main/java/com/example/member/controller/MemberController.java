package com.example.member.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.member.dto.MemberInfo;
import com.example.member.service.MemberService;
import com.example.member.vo.Greeting;
import com.example.member.vo.RequestMember;
import com.example.member.vo.ResponseMember;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member-service")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	private final Environment env;
	private final Greeting greeting;
	private final MemberService memberService;

	@GetMapping("/health-check")
	public String status() {
		return String.format(
			"it's Working in Member Service" + ",port(local.server.port)=" + env.getProperty("local.server.port")
				+ ",port(server.port)=" + env.getProperty("server.port"));
	}

	@PostMapping("/members")
	public ResponseEntity<ResponseMember> createMember(@Valid @RequestBody RequestMember request) {
		MemberInfo member = memberService.createMember(request.toMemberDto());
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseMember.fromInfo(member));
	}

	@GetMapping("/welcome")
	public String welcome(HttpServletRequest request) {
		log.info("users.welcome ip: {}, {}, {}, {}",
			request.getRemoteAddr(),
			request.getRemoteHost(),
			request.getRequestURI(),
			request.getRequestURL()
		);

		return greeting.getMessage();
	}
}

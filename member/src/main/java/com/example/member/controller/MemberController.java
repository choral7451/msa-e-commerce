package com.example.member.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.member.vo.Greeting;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	private final Environment env;
	private final Greeting greeting;

	@GetMapping("/health-check")
	public String status() {
		return String.format(
			"it's Working in Member Service" + ",port(local.server.port)=" + env.getProperty("local.server.port")
				+ ",port(server.port)=" + env.getProperty("server.port"));
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

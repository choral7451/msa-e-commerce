package com.example.member.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.member.dto.MemberInfo;
import com.example.member.service.MemberService;
import com.example.member.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final MemberService memberService;
	private final Environment env;

	public AuthenticationFilter(AuthenticationManager authenticationManager,
		MemberService memberService,
		Environment env) {
		super(authenticationManager);
		this.memberService = memberService;
		this.env = env;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException {
		try {
			RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

			return getAuthenticationManager().authenticate( // loadUserByUsername(email) 호출
				new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>())
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
		FilterChain chain, Authentication authResult) throws IOException, ServletException {
		String email = ((User)authResult.getPrincipal()).getUsername();
		MemberInfo memberInfo = memberService.getMemberByEmail(email);

		SecretKey secretKey = Keys.hmacShaKeyFor(env.getProperty("token.secret").getBytes());
		long expirationTime = Long.parseLong(env.getProperty("token.expiration_time"));

		String token = Jwts.builder()
			.subject(memberInfo.memberId())
			.expiration(new Date(System.currentTimeMillis() + expirationTime))
			.signWith(secretKey)
			.compact();

		response.addHeader("token", token);
		response.addHeader("memerId", memberInfo.memberId());
	}
}

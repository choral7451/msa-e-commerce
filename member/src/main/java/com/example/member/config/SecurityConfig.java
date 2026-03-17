package com.example.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import com.example.member.service.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final MemberServiceImpl memberServiceImpl;
	private final BCryptPasswordEncoder passwordEncoder;
	private final Environment env;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder.userDetailsService(memberServiceImpl).passwordEncoder(passwordEncoder);
		AuthenticationManager authenticationManager = authBuilder.build();

		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, memberServiceImpl, env);
		authenticationFilter.setFilterProcessesUrl("/login");

		http.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers("/actuator/**").permitAll()
				.requestMatchers("/**").access(
					new WebExpressionAuthorizationManager(
						"hasIpAddress('127.0.0.1') or hasIpAddress('::1') or hasIpAddress('172.16.241.64') or hasIpAddress('192.168.219.106')"
					)
				)
				.anyRequest().authenticated()
			)
			.authenticationManager(authenticationManager)
			.addFilter(authenticationFilter)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

		return http.build();
	}
}

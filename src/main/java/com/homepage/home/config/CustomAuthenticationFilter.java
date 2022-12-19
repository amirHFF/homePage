package com.homepage.home.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homepage.home.model.user.Account;
import com.homepage.home.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		Map<String, String> requestMap = new HashMap<>();
		String username;
		String password;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			if (request.getContentType().equals(APPLICATION_JSON_VALUE)) {
				requestMap = objectMapper.readValue(request.getInputStream(), HashMap.class);
			} else if (request.getContentType().equals(APPLICATION_FORM_URLENCODED_VALUE)) {
				requestMap = readRequest(request.getInputStream());
			}
			username = requestMap.get("username");
			password = requestMap.get("password");
			return super.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (AuthenticationException exception) {
			logger.error(exception.getMessage());
			exception.printStackTrace();
		} catch (Exception exception) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			HashMap<String, String> error = new HashMap<>();
			error.put("errorMessage", exception.getMessage());
			response.setContentType(APPLICATION_JSON_VALUE);
			try {
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("Error in attemptAuthentication", exception);
		}
		return super.attemptAuthentication(request, response);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
		Account user =(Account) authentication.getPrincipal();
		String accessToken = JwtUtil.generateAccessToken(user.getUsername(), request.getRequestURL().toString(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		String refreshToken = JwtUtil.generateRefreshToken(user.getUsername());
		response.setHeader("accessToken", accessToken);
		response.setHeader("refreshToken", refreshToken);
//		super.successfulAuthentication(request, response, chain, authentication);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(APPLICATION_JSON_VALUE);
		Map<String, String> error = new HashMap<>();
		error.put("errorMessage", "Bad credentials");
		new ObjectMapper().writeValue(response.getOutputStream(), error);
	}

	private Map<String, String> readRequest(ServletInputStream servletInputStream) throws IOException {
		Map<String, String> attributeMap = new HashMap<>();
		String body = new String(servletInputStream.readAllBytes(), StandardCharsets.UTF_8);

		for (String attribute : body.split("&")) {
			if (attribute.contains("username")) {
				String[] usernameAttribute= attribute.split("=");
				attributeMap.put("username", usernameAttribute[1].trim());
			}
			if (attribute.contains("password")) {
				String[] passwordAttribute= attribute.split("=");
				attributeMap.put("password", passwordAttribute[1].trim());
			}
		}
		return attributeMap;
	}
}

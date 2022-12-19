package com.homepage.home.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String accessToken = null;
		if (isPathAuthorize(request.getServletPath())) {
			filterChain.doFilter(request, response);
		} else {
			String authorization = request.getHeader("Authorization");
			if (authorization != null && authorization.startsWith("bearer")) {
				accessToken = authorization.substring("bearer ".length());
				try {
					UsernamePasswordAuthenticationToken authenticationToken = JwtUtil.parseToken(accessToken);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					super.doFilter(request,response,filterChain);
				} catch (Exception e) {
					logger.error(String.format("Error auth token: %s", accessToken), e);
					response.setStatus(HttpStatus.FORBIDDEN.value());
					Map<String, String> error = new HashMap<>();
					error.put("errorMessage", e.getMessage());
					response.setContentType(APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(), error);
				}
			}
		}
	}

	private boolean isPathAuthorize(String path){
		switch (path){
			case "/":
			case "/login":
			case "/refreshToken":
				return true;
			default:
				return false;
		}
	}
}

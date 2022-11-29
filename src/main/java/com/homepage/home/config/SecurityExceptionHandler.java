package com.homepage.home.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityExceptionHandler {
	public static AuthenticationFailureHandler authenticationFailureExceptionHandler() {
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
					throws IOException, ServletException {
				httpServletResponse.sendRedirect("/error-handler");

			}
		};
	}
	public static AccessDeniedHandler getAccessDenialHandler(){
		return new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
				httpServletResponse.sendRedirect("/access-denial");
			}
		};
	}
}

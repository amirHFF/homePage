package com.homepage.home.config;

import com.homepage.home.service.userAccount.AccountUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccountUserDetailsService userDetailsService;

	@Value("${authentication.type}")
	private String authenticationType;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
				.configurationSource(corsConfigurationSource())
				.and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/main", "/").hasRole("USER")
				.antMatchers("/login-form").permitAll()
				.antMatchers("/accounts", "/access-denial", "/userSetting").permitAll()
				.antMatchers("/accounts/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().accessDeniedPage("/access-denial").and()
				.formLogin().permitAll()
				.loginPage("/login").defaultSuccessUrl("/main")
				.failureUrl("/login?error=true");
		;
		if (authenticationType.equals("jwt")) {
			http.addFilter(new CustomAuthenticationFilter(super.authenticationManagerBean()))
					.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));
		configuration.setAllowCredentials(true);
		//the below three lines will add the relevant CORS response headers
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/webjars/**");
	}

}

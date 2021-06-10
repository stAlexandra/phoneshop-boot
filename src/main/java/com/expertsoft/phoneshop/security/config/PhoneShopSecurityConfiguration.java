package com.expertsoft.phoneshop.security.config;

import com.expertsoft.phoneshop.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.*;

@Configuration
@EnableWebSecurity
public class PhoneShopSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Resource
	private UserService userService;

	@Resource
	private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests(a -> a
					.antMatchers(ROOT_PATH, PHONES_PATH, ERROR_PATH, LOGIN_PATH).permitAll()
					.antMatchers( "/webjars/**", "/css/**").permitAll()
					.anyRequest().authenticated()
			)
			.oauth2Login()
				.userInfoEndpoint()
					.userService(oAuth2UserService).and()
				.loginPage(LOGIN_PATH).and()
			.formLogin()
				.loginPage(LOGIN_PATH)
				.loginProcessingUrl(FORM_LOGIN_PROCESSING_PATH)
				.failureUrl(FORM_LOGIN_ERROR_PATH);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

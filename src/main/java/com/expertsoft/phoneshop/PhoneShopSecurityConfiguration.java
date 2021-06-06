package com.expertsoft.phoneshop;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.expertsoft.phoneshop.PhoneShopConstants.*;

@Configuration
@EnableWebSecurity
public class PhoneShopSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(a -> a
					.antMatchers(ROOT_PATH, PHONES_PATH, ERROR_PATH, LOGIN_PATH).permitAll()
					.antMatchers( "/webjars/**", "/css/**").permitAll()
					.anyRequest().authenticated()
			)
			.oauth2Login()
			.loginPage(LOGIN_PATH);
	}
}

package com.expertsoft.phoneshop;

import com.expertsoft.phoneshop.persistence.repository.PhoneShopUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.*;

@Configuration
@EnableWebSecurity
public class PhoneShopSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Resource
	private PhoneShopUserRepository phoneShopUserRepository;

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
				.loginPage(LOGIN_PATH)
			.and()
			.formLogin()
				.loginPage(LOGIN_PATH)
				.loginProcessingUrl("/perform_login")
				.failureUrl("/login?error=true");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService((username) -> phoneShopUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User with name " + username + "could not be found.")));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

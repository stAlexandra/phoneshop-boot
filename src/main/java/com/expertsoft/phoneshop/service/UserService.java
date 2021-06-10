package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.persistence.model.user.PhoneShopUserDetails;
import com.expertsoft.phoneshop.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements UserDetailsService {

	@Value("${phoneshop.user.anonymous.display.name}")
	private String guestUserDisplayName;

	@Resource
	private AuthenticationService authenticationService;

	@Resource
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).map(PhoneShopUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + "could not be found."));
	}

	public String getCurrentUserDisplayName() {
		Authentication auth = authenticationService.getAuthentication();
		if (authenticationService.isAnonymous(auth)) {
			return guestUserDisplayName;
		} else {
			return auth.getName();
		}
	}
}

package com.expertsoft.phoneshop.security;

import com.expertsoft.phoneshop.persistence.model.user.GitHubOAuth2User;
import com.expertsoft.phoneshop.persistence.model.user.PhoneShopUser;
import com.expertsoft.phoneshop.persistence.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@Service
public class PhoneShopOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final OAuth2UserService<OAuth2UserRequest, OAuth2User> defaultOAuth2UserService = new DefaultOAuth2UserService();
	private static final String GITHUB_CLIENT_REGISTRATION_ID = "github";

	@Resource
	private UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);
		if (oAuth2User != null && GITHUB_CLIENT_REGISTRATION_ID.equals(userRequest.getClientRegistration().getRegistrationId())) {
			GitHubOAuth2User gitHubUser = loadGitHubUser(oAuth2User);
			updatePhoneShopUser(gitHubUser);
			return gitHubUser;
		}
		return oAuth2User;
	}

	protected GitHubOAuth2User loadGitHubUser(OAuth2User source) {
		GitHubOAuth2User target = new GitHubOAuth2User();
		target.setId(source.getAttribute(GitHubUserAttributes.ID));
		target.setEmail(source.getAttribute(GitHubUserAttributes.EMAIL));
		target.setLogin(source.getAttribute(GitHubUserAttributes.LOGIN));
		target.setDisplayName(source.getAttribute(GitHubUserAttributes.NAME));
		target.setBio(source.getAttribute(GitHubUserAttributes.BIO));
		target.setAvatarUrl(source.getAttribute(GitHubUserAttributes.AVATAR_URL));
		target.setLocation(source.getAttribute(GitHubUserAttributes.LOCATION));
		target.setCompany(source.getAttribute(GitHubUserAttributes.COMPANY));
		return target;
	}

	protected void updatePhoneShopUser(GitHubOAuth2User gitHubUser) {
		String githubName = gitHubUser.getName();
		PhoneShopUser phoneShopUser = userRepository.findByUsername(githubName).orElseGet(() -> {
			PhoneShopUser newUser = new PhoneShopUser(githubName);
			newUser.setRegistrationDate(LocalDateTime.now());
			return newUser;
		});
		phoneShopUser.setLogin(gitHubUser.getLogin());
		phoneShopUser.setDisplayName(gitHubUser.getDisplayName());
		phoneShopUser.setBio(gitHubUser.getBio());
		phoneShopUser.setAvatarUrl(gitHubUser.getAvatarUrl());
		phoneShopUser.setLocation(gitHubUser.getLocation());
		phoneShopUser.setCompany(gitHubUser.getCompany());
		userRepository.saveAndFlush(phoneShopUser);
	}
}

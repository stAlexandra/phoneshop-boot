package com.expertsoft.phoneshop.persistence.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitHubOAuth2User implements OAuth2User {
	private final List<GrantedAuthority> authorities =
			AuthorityUtils.createAuthorityList(UserRole.ROLE_USER);
	private Map<String, Object> attributes;
	private Integer id;
	private String displayName;
	private String login;
	private String email;
	private String bio;
	private String avatarUrl;
	private String location;
	private String company;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public Map<String, Object> getAttributes() {
		if (this.attributes == null) {
			this.attributes = new HashMap<>();
			this.attributes.put("id", this.getId());
			this.attributes.put("displayName", this.getDisplayName());
			this.attributes.put("login", this.getLogin());
			this.attributes.put("email", this.getEmail());
			this.attributes.put("bio", this.getBio());
			this.attributes.put("avatarUrl", this.getAvatarUrl());
			this.attributes.put("location", this.getLocation());
			this.attributes.put("company", this.getCompany());
		}
		return attributes;
	}

	@Override
	public String getName() {
		return login;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
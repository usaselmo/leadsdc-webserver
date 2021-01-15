package com.leadsdc.webserver.config;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.leadsdc.webserver.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LeadUserDetails implements UserDetails {

	private static final long serialVersionUID = -1890744816765055526L;

	private final User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.user.getProfiles().stream().map(pf -> new SimpleGrantedAuthority("ROLE_" + pf.getProfile().name().toUpperCase())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		if (StringUtils.hasText(this.user.getName()))
			return this.user.getName();
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return StringUtils.hasText(this.user.getPassword());
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}

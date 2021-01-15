package com.leadsdc.webserver.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.leadsdc.webserver.model.User;
import com.leadsdc.webserver.model.UserProfile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDTO {
	private final String id;
	private final String email; 
	private final String password;
	private final String name; 
	private final boolean enabled;
	private final CompanyDTO company;
  private final List<String> profiles;
  
	public static final UserDTO of(User user ) {
		if(user == null) return null;
		List<String> profs = user.getProfiles()==null||user.getProfiles().isEmpty()?Collections.emptyList():user.getProfiles().stream().map(p->p.getProfile().toString()).collect(Collectors.toList());
		return new UserDTO(
				String.valueOf(user.getId()), 
				user.getEmail(), 
				user.getPassword(), 
				user.getName(), 
				user.isEnabled(), 
				user.getCompany()!=null ? CompanyDTO.of(user.getCompany()) : null, 
				profs
		);
	}

	public static final User toUser(UserDTO ud) {
		if(ud == null) return null;
		User u = new User();
		u.setCompany(CompanyDTO.to(ud.getCompany()));
		u.setEmail(ud.getEmail());
		u.setEnabled(ud.isEnabled());
		u.setId(!StringUtils.hasText(ud.getId())?null:Long.valueOf(ud.getId()));
		u.setName(ud.getName());
		u.setPassword(ud.getPassword());
		u.setProfiles(ud.getProfiles().stream().map(p->UserProfile.builder().user(u).profile(UserProfile.Description.valueOf(p)).build()).collect(Collectors.toList()));
		return u;
	}

}

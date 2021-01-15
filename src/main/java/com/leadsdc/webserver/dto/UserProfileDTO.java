package com.leadsdc.webserver.dto;

import com.leadsdc.webserver.model.UserProfile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserProfileDTO {
	
	private final String id;
	private final String userId;
	private final String profile;
	
	public final static UserProfileDTO of(UserProfile userProfile) {
		if(userProfile == null) return null;
		return new UserProfileDTO(
				String.valueOf(userProfile.getId()), 
				userProfile.getUser()!=null ? String.valueOf(userProfile.getUser().getId()) : "", 
				userProfile.getProfile() != null ? userProfile.getProfile().toString() : ""
		);
	}
	
}

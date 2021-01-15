package com.leadsdc.webserver.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
public class LeadEntity {

	private final List<String> successMessages = new ArrayList<>();
	private final List<String> errorMessages = new ArrayList<>();
	private final Map<String, Object> map = new HashMap<>();

	public LeadEntity addSuccessMessage(String successMessage) {
		this.successMessages.add(successMessage);
		return this;
	}

	public LeadEntity addErrorMessage(String errorMessage) {
		this.errorMessages.add(errorMessage);
		return this;
	}
	
	public LeadEntity addToMap(String key, Object object) {
		this.map.put(key, object);
		return this;
	}

	public static enum Key {
		lead, leads, events, leadTypes, totalLeads, company, companies, proposal, person, persons, invitation, user, users, profiles
	}

}

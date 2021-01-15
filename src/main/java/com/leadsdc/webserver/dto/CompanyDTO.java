package com.leadsdc.webserver.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.leadsdc.webserver.model.Company;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CompanyDTO {
	
	private final String id;
	private final String name;
	private final String email;
	private final String address;
	private final String website;

	public static final CompanyDTO of(Company company) {
		if(company == null) return null;
		return new CompanyDTO(
				company.getId()!=null ? String.valueOf(company.getId()) : "", 
				company.getName(), 
				company.getEmail(), 
				company.getAddress(), 
				company.getWebsite()
		);
	}
	
	public static final List<CompanyDTO> of(List<Company>  companies){
		if(companies == null) return Collections.emptyList();
		return companies.stream().map(c-> CompanyDTO.of(c)).collect(Collectors.toList());
	}

	public static final Company to(CompanyDTO companyDTO) {
		if(companyDTO == null) return null;
		Company c = new Company();
		c.setAddress(companyDTO.getAddress());
		c.setEmail(companyDTO.getEmail());
		c.setId(Long.valueOf(companyDTO.getId()));
		c.setName(companyDTO.getName());
		c.setWebsite(companyDTO.getWebsite());
		return c;
	}
	
}

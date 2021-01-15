package com.leadsdc.webserver.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.leadsdc.webserver.model.Person;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PersonDTO {

	private final String id;
	private final String email;
	private final String name;
	private final String address;
	private final String cellPhone;
	private final String phone;
	private final CompanyDTO company;

	public static final PersonDTO of(Person person) {
		if(person == null) return null;
		return new PersonDTO(String.valueOf(person.getId()), person.getEmail(), person.getName(), person.getAddress(), person.getCellPhone(), person.getPhone(), CompanyDTO.of(person.getCompany()));
	}

	public static final Person toPerson(PersonDTO cd) {
		if(cd == null) return null;
		Person c = new Person();
		c.setAddress(cd.getAddress());
		c.setCellPhone(cd.getCellPhone());
		c.setEmail(cd.getEmail());
		c.setId(!StringUtils.hasText(cd.getId()) ? null : Long.valueOf(cd.getId()));
		c.setName(cd.getName());
		c.setPhone(cd.getPhone());
		c.setCompany(CompanyDTO.to(cd.getCompany()));
		return c;
	}

	public static List<PersonDTO> of(List<Person> persons) {
		if(persons == null) return Collections.emptyList();
		return persons.stream().map(person -> of(person)).collect(Collectors.toList());
	}

	public static final List<Person> toPerson(List<PersonDTO> dtos) {
		if(dtos == null) return Collections.emptyList();
		return dtos.stream().map(dto -> PersonDTO.toPerson(dto)).collect(Collectors.toList());
	}
}

package com.leadsdc.webserver.dto;

import java.util.stream.Stream;

import org.springframework.util.StringUtils;

import com.leadsdc.webserver.type.Event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EventDTO {

	private final String name;
	private final String status ; 
	private final String action ;
	private final String abbreviation;
	
	public static EventDTO of(Event event) {
		if(event==null)	return null;
		return new EventDTO(event.name(), event.getAction(), event.getStatus(), event.getAbbreviation());
	}
	
	public static Event to(EventDTO eventTypeDTO) {
		if(eventTypeDTO==null) return null;
		if(StringUtils.hasText(eventTypeDTO.getAction()))
			return Stream.of(Event.values()).filter(e -> e.getAction().equals(eventTypeDTO.getAction())).findFirst().orElse(null);
		else
			return Event.valueOf(eventTypeDTO.getStatus());
	}
	
}

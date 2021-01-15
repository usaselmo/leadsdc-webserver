package com.leadsdc.webserver.dto;

import com.leadsdc.webserver.model.EventLog;
import com.leadsdc.webserver.service.Converter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EventLogDTO {
	private final String id;
	private final String objectName;
	private final String objectId;
	private final String eventType;
	private final String eventTime;
	private final UserDTO user;
	private final String message;
	
	public static EventLogDTO of(EventLog e) {
		if(e==null)
			return null;
		return new EventLogDTO(e.getId()!=null?String.valueOf(e.getId()):"", e.getObjectName(), e.getObjectId(), e.getEventType(), Converter.dateToString(e.getEventTime()), UserDTO.of(e.getUser()), e.getMessage());
	}
	
}

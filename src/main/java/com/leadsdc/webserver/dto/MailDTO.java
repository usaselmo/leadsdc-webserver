package com.leadsdc.webserver.dto;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MailDTO {

	private final List<PersonDTO> to;
	private final List<PersonDTO> bcc;
	private final String text;
	private final List<MediaDTO> attachments;
	private final String type;
	private final String subject;

}

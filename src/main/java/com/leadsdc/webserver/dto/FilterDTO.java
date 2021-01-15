package com.leadsdc.webserver.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FilterDTO {

	private final int pageRange;
	private final int lines;
	private final String searchText;
	private final String event;

}

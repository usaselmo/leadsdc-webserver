package com.leadsdc.webserver.dto;

import com.leadsdc.webserver.model.Line;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LineDTO {
	private final String id;
	private final String description;

	public static final LineDTO of(Line line) {
		if(line == null) return null;
		return new LineDTO(line.getId() != null ? String.valueOf(line.getId()) : "", line.getDescription());
	}

	public static final Line toLine(LineDTO lineDTO) {
		if(lineDTO == null) return null;
		Line line = new Line();
		line.setDescription(lineDTO.getDescription());
		line.setId(lineDTO.getId() == null ? null : Long.valueOf(lineDTO.getId()));
		return line;
	}

}

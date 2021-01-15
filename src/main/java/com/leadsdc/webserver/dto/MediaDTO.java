package com.leadsdc.webserver.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.leadsdc.webserver.model.Media;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MediaDTO {
	
	private final String id;
	private final byte[] content;
	private final String type;
	private final String name;

	public static final MediaDTO of(Media media) {
		if(media == null) return null;
		return new MediaDTO(
				media.getId()!=null ? String.valueOf(media.getId()): "", 
				media.getContent(), 
				media.getType(), 
				media.getName()
		);
	}
	
	public static final List<MediaDTO> of(List<Media> medias){
		if(medias==null) return Collections.emptyList();
		return medias.stream().map(media-> MediaDTO.of(media)).collect(Collectors.toList());
	}
	
	public static final Media toMedia(MediaDTO mediaDTO) {
		if(mediaDTO==null) return null;
		return new Media(Long.valueOf(mediaDTO.getId()), mediaDTO.getContent(), mediaDTO.getType(), mediaDTO.getName());
	}
	
	public static final List<Media> toMedia( List<MediaDTO> mediaDTOs){
		if(mediaDTOs==null) return Collections.emptyList();
		return mediaDTOs.stream().map(m->MediaDTO.toMedia(m)).collect(Collectors.toList());
	}
	
}

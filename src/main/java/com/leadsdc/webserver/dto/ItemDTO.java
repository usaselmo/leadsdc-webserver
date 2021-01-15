package com.leadsdc.webserver.dto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.leadsdc.webserver.model.Item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemDTO {
	private final String id;
	private final String title;
	private final String price;
	private final List<LineDTO> lines;

	public static final ItemDTO of(Item item) {
		if(item == null) return null;
		return new ItemDTO(
				item.getId()!=null?String.valueOf(item.getId()):"", 
				item.getTitle(), 
				item.getPrice()!=null?item.getPrice().toString().replaceAll("$",	""):"", 
				item.getLines()!=null?item.getLines().stream().map(l->LineDTO.of(l)).collect(Collectors.toList()):Collections.emptyList()
		);
	}
	
	public static final Item toItem(ItemDTO itemDTO) {
		if(itemDTO == null) return null;
		Item item = new Item();
		item.setId(!StringUtils.hasText(itemDTO.getId())?null:Long.valueOf(itemDTO.getId()));
		item.setLines(itemDTO.getLines()!=null?itemDTO.getLines().stream().map(l->LineDTO.toLine(l)).collect(Collectors.toList()):Collections.emptyList());
		item.setPrice(!StringUtils.hasText(itemDTO.getPrice())?BigDecimal.ZERO:new BigDecimal(itemDTO.getPrice().replace("$", "")));
		item.setTitle(itemDTO.getTitle());
		return item;
	}
	
}

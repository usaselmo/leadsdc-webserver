package com.leadsdc.webserver.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
@Table
public class Media implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)	private Long id;
	@Lob private	byte[] content;
	private String type;
	private String name;

}

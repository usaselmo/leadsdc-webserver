package com.leadsdc.webserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsdc.webserver.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	
	
}

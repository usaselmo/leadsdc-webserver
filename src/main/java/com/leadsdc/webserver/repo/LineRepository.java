package com.leadsdc.webserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsdc.webserver.model.Line;

public interface LineRepository extends JpaRepository<Line, Long>{

}

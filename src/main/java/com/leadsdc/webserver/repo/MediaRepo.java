package com.leadsdc.webserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsdc.webserver.model.Media;

public interface MediaRepo extends JpaRepository<Media, Long> {

}

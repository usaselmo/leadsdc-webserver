package com.leadsdc.webserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsdc.webserver.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}

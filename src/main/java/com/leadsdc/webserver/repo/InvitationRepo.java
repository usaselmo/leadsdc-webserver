package com.leadsdc.webserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsdc.webserver.model.Invitation;

public interface InvitationRepo extends JpaRepository<Invitation, Long> {

}

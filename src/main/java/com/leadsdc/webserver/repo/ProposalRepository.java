package com.leadsdc.webserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leadsdc.webserver.model.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Long>{
	
}

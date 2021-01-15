package com.leadsdc.webserver.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leadsdc.webserver.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("SELECT c FROM Person c WHERE c.name LIKE %?1% OR c.email LIKE %?1% OR c.address LIKE %?1% OR c.phone LIKE %?1% ORDER BY c.name")
	List<Person> findByName(String name);

}

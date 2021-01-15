package com.leadsdc.webserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leadsdc.webserver.model.User;
import com.leadsdc.webserver.repo.UserRepository;

@Service
public class LeadDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new LeadUserDetails(user);
	}

}

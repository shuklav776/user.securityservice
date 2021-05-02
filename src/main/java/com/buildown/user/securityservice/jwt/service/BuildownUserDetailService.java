package com.buildown.user.securityservice.jwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BuildownUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userMailOrPhone) throws UsernameNotFoundException {

		if (userMailOrPhone.equals("vshukla@workllama.com")) {
			return new User("vshukla@workllama.com", "Pass@123", new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found!!");
		}
	}

}

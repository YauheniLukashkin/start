package com.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.library.enums.Role;
import com.library.service.iface.AutoLoginService;

@Service
public class AutoLoginServiceImpl implements AutoLoginService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService; 
		
	@Override
	public void autoLogin(String username, String password, Role role) {
		List<Role> roleList = new ArrayList<>();
		roleList.add(role);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(userDetails, password, roleList);
		authenticationManager.authenticate(authenticationToken);
		if (authenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
	}
}

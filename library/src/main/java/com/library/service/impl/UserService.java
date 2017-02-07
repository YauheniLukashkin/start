package com.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.entity.Librarian;
import com.library.entity.Reader;
import com.library.enums.Role;
import com.library.repository.LibrarianRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private ReaderServiceImpl readerService;
	
	@Autowired
	LibrarianRepository librarians;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserDetails loadedUser;
		try {
			Reader reader = readerService.findByLogin(login);
			List<GrantedAuthority> role = new ArrayList<>();
			if(reader != null){
				role.add(Role.READER);
				loadedUser = new org.springframework.security.core.userdetails.User(
						reader.getLogin(), reader.getPassword(), role);
			} else {
				role.add(Role.LIBRARIAN);
				Librarian librarian = librarians.findByLogin(login);
				loadedUser = new org.springframework.security.core.userdetails.User(
						librarian.getLogin(), librarian.getPassword(), role);
			}
		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}
		return loadedUser;
	}
}
package com.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.entity.Librarian;
import com.library.repository.LibrarianRepository;
import com.library.service.iface.LibrarianService;

@Service
public class LibrarianServiceImpl implements LibrarianService {
	@Autowired
	private LibrarianRepository librarians;
	
	@Transactional
	public Librarian findByLogin(String login) {
		return  librarians.findByLogin(login);
	}

	@Transactional
	public void saveLibrarian(Librarian librarian) {
		librarians.save(librarian);
	}	
}

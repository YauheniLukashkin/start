package com.library.repository;


import org.springframework.data.repository.CrudRepository;

import com.library.entity.Librarian;

public interface LibrarianRepository  extends CrudRepository<Librarian, Long>{
	Librarian findByLogin(String login);
}

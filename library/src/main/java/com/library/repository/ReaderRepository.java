package com.library.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.library.entity.Reader;

public interface ReaderRepository  extends CrudRepository<Reader, Long> {
	Reader findByName(String name);
	Reader findByLogin(String login);
	List<Reader> findByLoginContaining(String login);
}

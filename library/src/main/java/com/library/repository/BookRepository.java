package com.library.repository;
import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.library.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long>{
	List<Book> findByIdNotIn(Collection<Long> ids);
	List<Book> findAll();
}
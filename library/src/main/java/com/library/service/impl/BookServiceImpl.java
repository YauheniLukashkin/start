package com.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.entity.Book;
import com.library.repository.BookRepository;
import com.library.service.iface.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository books;

	@Transactional
	public void addBook(Book book) {
		books.save(book);
	}		
}

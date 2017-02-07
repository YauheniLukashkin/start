package com.library.service.impl;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.entity.Book;
import com.library.entity.Librarian;
import com.library.entity.Reader;
import com.library.entity.Reading;
import com.library.repository.BookRepository;
import com.library.repository.ReadingRepository;
import com.library.service.iface.ReadingService;

@Service
public class ReadingServiceImpl implements ReadingService{
	@Autowired
	private ReadingRepository reading;
	@Autowired
	private BookRepository books;
	
	@Transactional
	public void returnInLibraryAll(String[] idReadind) {
		Date date = new Date(System.currentTimeMillis());
		Reading read;
		for(String id: idReadind){
			read = reading.findOne(Long.parseLong(id));
			read.setDateFinish(date);
		}
	}

	public void addAll(Librarian librarian, Reader reader, String[] idBooks) {
		for(String id: idBooks){
			addReading(librarian, reader, books.findOne(Long.parseLong(id)));
		}
	}
	@Transactional
	private void addReading(Librarian librarian, Reader reader, Book book) {
		Reading read = new Reading(); 
		read.setDateStart(new Date(System.currentTimeMillis()));
		read.setLibrarian(librarian);
		read.setReader(reader);
		read.setBook(book);
		reading.save(read);
	}
}

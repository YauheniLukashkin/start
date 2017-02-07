package com.library.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.entity.Reading;
import com.library.facade.LibraryFacade;
import com.library.repository.BookRepository;
import com.library.repository.ReadingRepository;
import com.library.service.iface.FacadeLibrarySirvice;

@Service
public class FacadeLibrarySirviceImpl implements FacadeLibrarySirvice{
	@Autowired
	private BookRepository books;
	@Autowired
	private ReadingRepository reading;
		
	@Transactional
	public void initFacadeLibrary(LibraryFacade facade) {
		List<Reading> readNow = reading.findByDateFinishIsNull();
		facade.setReadNow(readNow);
		Collection<Long> ids = new ArrayList<>();
		for(Reading reading:readNow){
			ids.add(reading.getBook().getId());
		}
		if(ids.size()>0){
			facade.setBooksLibrary(books.findByIdNotIn(ids));
		} else {
			facade.setBooksLibrary(books.findAll());
		}
		facade.setHistory((List<Reading>) reading.findAll());
	}
}

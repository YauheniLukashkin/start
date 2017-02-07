package com.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.entity.Reader;
import com.library.repository.ReaderRepository;
import com.library.service.iface.ReaderService;

@Service
public class ReaderServiceImpl implements ReaderService {
	@Autowired
	private ReaderRepository readers;
		
	@Transactional
	public Reader findByLogin(String login) {
		return  readers.findByLogin(login);
	}

	@Transactional
	public void saveReader(Reader reader) {
		readers.save(reader);
	}

	@Override
	public List<Reader> findByLoginContaining(String login) {
		return readers.findByLoginContaining(login);
	}	
}

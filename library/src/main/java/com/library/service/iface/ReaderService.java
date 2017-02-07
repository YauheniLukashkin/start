package com.library.service.iface;

import java.util.List;

import com.library.entity.Reader;

public interface ReaderService {
	public Reader findByLogin(String login);
	public void saveReader(Reader reader);
	List<Reader> findByLoginContaining(String login);
}

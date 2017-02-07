package com.library.service.iface;

import com.library.entity.Librarian;

public interface LibrarianService {
	public Librarian findByLogin(String login);
	public void saveLibrarian(Librarian reader);
}

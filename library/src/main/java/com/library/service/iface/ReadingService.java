package com.library.service.iface;

import com.library.entity.Librarian;
import com.library.entity.Reader;

public interface ReadingService {
	void returnInLibraryAll(String[] idReadings);
	void addAll(Librarian librarian, Reader reader, String[] idBooks);
}

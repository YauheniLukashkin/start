package com.library.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.library.entity.Reading;

public interface ReadingRepository extends CrudRepository<Reading, Long> {
	List<Reading> findByDateFinishIsNull();
}

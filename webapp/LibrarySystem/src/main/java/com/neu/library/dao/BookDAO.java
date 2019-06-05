package com.neu.library.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.neu.library.model.Book;

@Service
public class BookDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Book saveBook(Book book) {
		this.entityManager.persist(book);
		return book;
	}
}

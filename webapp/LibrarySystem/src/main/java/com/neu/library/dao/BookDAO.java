package com.neu.library.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.neu.library.model.Book;

@Service
public class BookDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Book saveBook(Book book) {
		this.entityManager.persist(book);
		return book;
	}
	
	public Book getBookById(String id) {
		
		Book book;
		book = entityManager.find(Book.class,id);
		return book;
		
		
	}
	
	@Transactional
	public void deleteById(Book book) {
			entityManager.remove(book);
	flushAndClear();
		
	}
	
	void flushAndClear() {
	    entityManager.flush();
	    entityManager.clear();
	}
}

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
}

package com.neu.library.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
	
	public List<Book> getBooks(){
		Query query = this.entityManager.createQuery("FROM Book");
		List<Book> bookList = query.getResultList();
		return bookList;	
	}
}

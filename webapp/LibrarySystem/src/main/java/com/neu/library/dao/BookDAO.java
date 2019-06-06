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
	
	@Transactional
	public List<Book> getBooks(){
		Query query = this.entityManager.createQuery("FROM Book");
		List<Book> bookList = query.getResultList();
		return bookList;
	}	
	
	@Transactional
	public Book getBookById(String id) {
		
		Book book;
		book = entityManager.find(Book.class,id);
		return book;	
	}
	
	@Transactional
	public Book updateBook(Book book, String id) {
		Book bookToBeUpdated = this.entityManager.find(Book.class, id);
		bookToBeUpdated.setTitle(book.getTitle());
		bookToBeUpdated.setAuthor(book.getAuthor());
		bookToBeUpdated.setIsbn(book.getIsbn());
		bookToBeUpdated.setQuantity(book.getQuantity());
		flushAndClear();
		return bookToBeUpdated;
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

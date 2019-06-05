package com.neu.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.neu.library.dao.BookDAO;
import com.neu.library.json.BookJson;
import com.neu.library.model.Book;


public class BookService {
	@Autowired
		BookDAO bookdao;
	public BookService() {
		
	}
	
	public ResponseEntity<Object> addBook(Book book){
		Book newBook = new Book();
		newBook.setTitle(book.getTitle());
		newBook.setAuthor(book.getAuthor());
		newBook.setIsbn(book.getIsbn());
		newBook.setQuantity(book.getQuantity());
		newBook = this.bookdao.saveBook(newBook);
		return new ResponseEntity<Object>(new BookJson(newBook) , HttpStatus.CREATED);
		
	}
}

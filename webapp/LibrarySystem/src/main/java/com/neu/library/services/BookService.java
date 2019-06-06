package com.neu.library.services;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.neu.library.dao.BookDAO;
import com.neu.library.json.BookJson;
import com.neu.library.model.Book;
import com.neu.library.response.ApiResponse;

@Service
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
	
	
	public ResponseEntity<Object> getBookById(String id){
		Book book;
		
		try {
			
		book=bookdao.getBookById(id);		
		}
		catch(NoResultException e){
			ApiResponse resp = new ApiResponse(HttpStatus.NOT_FOUND, "The requested resource could not be found", "Resource not available");
			return new ResponseEntity<Object>(resp, HttpStatus.NOT_FOUND);
		}
		
		if(book != null) {
	
			return new ResponseEntity<Object>(new BookJson(book), HttpStatus.OK);
		}
		
		else {
			ApiResponse resp = new ApiResponse(HttpStatus.NOT_FOUND, "The requested resource could not be found", "Resource not available");
			return new ResponseEntity<Object>(resp, HttpStatus.NOT_FOUND);
			
		}
}
	
	
	
	public ResponseEntity<Object> deleteBookById(String id){
		
		try {
			
			Book book= bookdao.getBookById(id);
			if(book != null)
			{	bookdao.deleteById(book);
			}else {
				ApiResponse resp = new ApiResponse(HttpStatus.NOT_FOUND, "The requested resource could not be found", "Resource not available");
				return new ResponseEntity<Object>(resp, HttpStatus.NOT_FOUND);
			}
		}
		
		catch(NoResultException e){
			ApiResponse resp = new ApiResponse(HttpStatus.NOT_FOUND, "The requested resource could not be found", "Resource not available");
			return new ResponseEntity<Object>(resp, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(null ,HttpStatus.NO_CONTENT);
	}
	
}

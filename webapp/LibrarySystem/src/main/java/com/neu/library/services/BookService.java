package com.neu.library.services;

import java.util.ArrayList;
import java.util.List;
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

	public ResponseEntity<Object> addBook(Book book) {
		Book newBook = new Book();
		newBook.setTitle(book.getTitle());
		newBook.setAuthor(book.getAuthor());
		newBook.setIsbn(book.getIsbn());
		newBook.setQuantity(book.getQuantity());
		newBook = this.bookdao.saveBook(newBook);
		return new ResponseEntity<Object>(new BookJson(newBook), HttpStatus.CREATED);

	}

	public ResponseEntity<Object> getBooks() {

		List<BookJson> books = new ArrayList<BookJson>();
		for (Book book : bookdao.getBooks()) {
			books.add(new BookJson(book));
		}

		if (books.isEmpty()) {
			ApiResponse resp = new ApiResponse(HttpStatus.NOT_FOUND, "The requested resource could not be found",
					"Resource not available");
			return new ResponseEntity<Object>(resp, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(books, HttpStatus.OK);
		}
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
	
	public ResponseEntity<Object> updateBook(Book book){
		Book bookFromDb;
		ApiResponse apiResponse;
		bookFromDb = bookdao.getBookById(book.getId());
		
		if(bookFromDb == null) {
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "The requested resource could not be found", "Resource not available");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}else {
			this.bookdao.updateBook(book, bookFromDb.getId());
			apiResponse = new ApiResponse(HttpStatus.NO_CONTENT, "The requested resource has been updated", "Resource updated successfully");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.NO_CONTENT);
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

 package com.neu.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neu.library.model.Book;
import com.neu.library.response.ApiResponse;
import com.neu.library.services.BookService;

@RestController
public class BookController {
	@Autowired
	BookService bookservice;

	@RequestMapping(value = "/bookakshay", method = RequestMethod.POST)
	public ResponseEntity<Object> addBook(@RequestBody Book book) {
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ApiResponse errorResponse;
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials") || message.equals("Username not entered") || message.equals("Password not entered")) {
			errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
		}
		if (StringUtils.isEmpty(book.getTitle())) {
			errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Title", "Please Enter Title");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getAuthor())) {
			errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Author", "Please Enter Author");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getIsbn())) {
			errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter ISBN", "Please Enter ISBN");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getQuantity())) {
			errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Quantity", "Please Enter Qunatity");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		return this.bookservice.addBook(book);
	}
	
	
	@RequestMapping(value="/book", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllBooks(){
		
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ApiResponse apiResponse;
		
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials")|| message.equals("Username not entered") || message.equals("Password not entered")) {
			apiResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(apiResponse, HttpStatus.UNAUTHORIZED);
		}
		
		return this.bookservice.getBooks();
		
	}
	
	@RequestMapping(value="/book", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateBook(@RequestBody Book book){
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ApiResponse apiResponse;
		
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials")|| message.equals("Username not entered") || message.equals("Password not entered")) {
			apiResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(apiResponse, HttpStatus.UNAUTHORIZED);
		}
		
		if(StringUtils.isEmpty(book.getId())) {
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter ID", "Please Enter ID");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getTitle())) {
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Title", "Please Enter Title");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getAuthor())) {
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Author", "Please Enter Author");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getIsbn())) {
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter ISBN", "Please Enter ISBN");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getQuantity())) {
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Quantity", "Please Enter Qunatity");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		
		return this.bookservice.updateBook(book);
	}
	
	@RequestMapping(value= "/book/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getBook(@PathVariable @NonNull String id){
		ApiResponse errorResponse;
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials") || message.equals("Username not entered") || message.equals("Password not entered")) {
			errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
		}
		
		return bookservice.getBookById(id);
		
		
	}
	
	
	@RequestMapping(value="book/{id}" ,method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBook(@PathVariable @NonNull String id){
		ApiResponse errorResponse;
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials") || message.equals("Username not entered") || message.equals("Password not entered")) {
			errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
		}
		return bookservice.deleteBookById(id);
		
	}
	
}

package com.neu.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
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

	@RequestMapping(value = "/book", method = RequestMethod.POST)
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
}

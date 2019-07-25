 package com.neu.library.controllers;
 import java.util.logging.FileHandler;



import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
import com.neu.library.services.StatMetric;

@RestController
public class BookController {
	@Autowired
	BookService bookservice;
	@Autowired
	 StatMetric statsMetric;

	//private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	Logger logger = Logger.getLogger("LibrarySystemLog"); 
		FileHandler fh;
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity<Object> addBook(@RequestBody Book book) {
		logger.info("Adding book with ID :"+book.getId());
		statsMetric.increementStat("POST /book");
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ApiResponse errorResponse;
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials") || message.equals("Username not entered") || message.equals("Password not entered")) {
			logger.info("Username and invalid credentials");
			errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
		}
		if (StringUtils.isEmpty(book.getTitle())) {
			logger.info("Title is needed");
			errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Title", "Please Enter Title");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getAuthor())) {
			logger.info("Author is needed");
			errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Author", "Please Enter Author");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getIsbn())) {
			logger.info("ISBN is needed");
			errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter ISBN", "Please Enter ISBN");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getQuantity())) {
			logger.info("Quantity is needed");
			errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Quantity", "Please Enter Qunatity");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		logger.info("Book added with ID :"+book.getId());
		return this.bookservice.addBook(book);
	}
	
	
	@RequestMapping(value="/book", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllBooks(){
		statsMetric.increementStat("GET /book");
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ApiResponse apiResponse;
		
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials")|| message.equals("Username not entered") || message.equals("Password not entered")) {
			logger.error("Username and invalid credentials");
			apiResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(apiResponse, HttpStatus.UNAUTHORIZED);
		}
		logger.info("Get all the books");
		return this.bookservice.getBooks();
		
	}
	
	@RequestMapping(value="/book", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateBook(@RequestBody Book book){
		statsMetric.increementStat("PUT /book");
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ApiResponse apiResponse;
		
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials")|| message.equals("Username not entered") || message.equals("Password not entered")) {
			logger.error("Username and invalid credentials");
			apiResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(apiResponse, HttpStatus.UNAUTHORIZED);
		}
		
		if(StringUtils.isEmpty(book.getId())) {
			logger.error("ID is needed");
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter ID", "Please Enter ID");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getTitle())) {
			logger.error("Title is needed");
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Title", "Please Enter Title");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getAuthor())) {
			logger.error("Author is needed");
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Author", "Please Enter Author");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getIsbn())) {
			logger.error("ISBN is needed");
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter ISBN", "Please Enter ISBN");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(book.getQuantity())) {
			logger.error("Quantity is needed");
			apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Please Enter Quantity", "Please Enter Qunatity");
			return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
		}
		logger.info("Update Book with ID"+book.getId());
		return this.bookservice.updateBook(book);
	}
	
	@RequestMapping(value= "/book/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getBook(@PathVariable @NonNull String id){
		statsMetric.increementStat("GET /book/{id}");
		ApiResponse errorResponse;
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials") || message.equals("Username not entered") || message.equals("Password not entered")) {
			logger.error("Username and invalid credentials");
			errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
		}
		logger.info("GET Book with ID"+id);
		return bookservice.getBookById(id);
		
		
	}
	
	
	@RequestMapping(value="book/{id}" ,method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBook(@PathVariable @NonNull String id){
		statsMetric.increementStat("DELETE /book/{id}");
		ApiResponse errorResponse;
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials") || message.equals("Username not entered") || message.equals("Password not entered")) {
			logger.error("Username and invalid credentials");
			errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
		}
		logger.info("DELETE Book with ID"+id);
		return bookservice.deleteBookById(id);
		
	}
	
}

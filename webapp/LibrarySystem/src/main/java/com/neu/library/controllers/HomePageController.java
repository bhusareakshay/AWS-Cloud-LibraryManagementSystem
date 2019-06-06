package com.neu.library.controllers;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neu.library.response.ApiResponse;

@RestController
public class HomePageController {
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<Object> displayDate(){
		ApiResponse apiResponse;
		
		apiResponse = new ApiResponse(HttpStatus.OK, new Date().toString(), "NA");
		
		return new ResponseEntity<Object>(apiResponse, new HttpHeaders(),HttpStatus.OK);
	}

}

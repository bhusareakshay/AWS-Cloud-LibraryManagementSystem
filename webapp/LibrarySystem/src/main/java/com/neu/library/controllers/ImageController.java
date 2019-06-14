package com.neu.library.controllers;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neu.library.response.ApiResponse;
import com.neu.library.services.ImageService;

@RestController
public class ImageController {
	@Autowired
	ImageService imageService;

	@RequestMapping(value = "/book/{bookId}/image", method = RequestMethod.POST)
	public ResponseEntity<Object> addAttachmentToNote(@PathVariable @NotNull String bookId,
			@RequestParam("file") MultipartFile file) {
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ApiResponse errorResponse;
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials")|| message.equals("Username not entered") || message.equals("Password not entered")) {
			errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
		}
		return this.imageService.addAttachmenttoBook(bookId, file);
}
	
	@RequestMapping(value = "/book/{bookId}/image/{imageId}", method = RequestMethod.DELETE	)
	public ResponseEntity<Object> deleteAttachmentToNote(@PathVariable("bookId") @NotNull String bookId, @PathVariable("imageId") @NotNull String imageId) {
		String message = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ApiResponse errorResponse;
		if (message.equals("Username does not exist") || message.equals("Invalid Credentials")|| message.equals("Username not entered") || message.equals("Password not entered")) {
			errorResponse = new ApiResponse(HttpStatus.UNAUTHORIZED, message, message);
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
		}
		return this.imageService.delete(bookId, imageId);
	}
	
	
	
	
}

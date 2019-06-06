package com.neu.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.neu.library.dao.UserDAO;
import com.neu.library.controllers.UserRegisterController;
import com.neu.library.json.UserCredential;
import com.neu.library.model.User;
import com.neu.library.response.ApiResponse;
import com.neu.library.util.BCryptUtil;
import com.neu.library.util.ValidateUtil;

@Service
public class UserRegisterService {

	@Autowired
	UserRegisterController userController;

	@Autowired
	private BCryptUtil bCrptUtil;

	@Autowired
	private ValidateUtil validUtil;

	@Autowired
	UserDAO userDao;

	@Autowired
	LoginService loginService;

	public ResponseEntity<Object> registerUser(UserCredential cred) {

		if (!validUtil.varifyEmail(cred.getUsername())) {

			ApiResponse apiError = new ApiResponse(HttpStatus.BAD_REQUEST,
					"Invalid syntax for this request was provided.", "Not an valid email address");
			return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
		} else if (loginService.checkIfUserExists(cred.getUsername())) {
			ApiResponse apiError = new ApiResponse(HttpStatus.BAD_REQUEST,
					"Invalid syntax for this request was provided.", "User with same email already exists");
			return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
		} else if (!validUtil.verifyPassword(cred.getPassword())) {
			ApiResponse apiError = new ApiResponse(HttpStatus.BAD_REQUEST,
					"Invalid syntax for this request was provided.",
					"Password must contain more than 1 character and no whitespaces");
			return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
		} else {

			User user = new User(cred.getUsername(), bCrptUtil.generateEncryptedPassword(cred.getPassword()));
			userDao.saveUser(user);
			ApiResponse apiresponse = new ApiResponse(HttpStatus.OK, "User has been successfully registered", "NA");
			return new ResponseEntity<Object>(apiresponse, HttpStatus.OK);

		}
	}
}

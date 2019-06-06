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

@Service
public class UserRegisterService {
	
	
	@Autowired
	UserRegisterController userController;
	
	@Autowired
	private BCryptUtil bCrptUtil;
	
	@Autowired
	UserDAO userDao;
	
	public ResponseEntity<Object> registerUser(UserCredential cred){
		User user = new User(cred.getUsername(),bCrptUtil.generateEncryptedPassword(cred.getPassword()));
		
		userDao.saveUser(user);
		ApiResponse apiresponse = new ApiResponse(HttpStatus.OK, "User has been successfully registered", "NA");
		return new ResponseEntity<Object>(apiresponse, HttpStatus.OK);
		
	}

}

package com.neu.library.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.neu.library.services.LoginService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private LoginService loginService;

	@Override
	public Authentication authenticate(Authentication auth) {
		// System.out.println("inside CustomAuthenticationProvider");

		UsernamePasswordAuthenticationToken authenticationToken = null;
		try {
			String username = "";
			String password = "";
			try {
				
				username = String.valueOf(auth.getName());
				password = String.valueOf(auth.getCredentials().toString());

			} catch (Exception e) {
				username = "";
				password = "";
			}

			try {

				if ((username == null || username.contentEquals(""))
						&& (password == null || password.contentEquals(""))) {
					
					authenticationToken = new UsernamePasswordAuthenticationToken("User not logged in", "",
							new ArrayList<>());
					return authenticationToken;
				}

				if (username == null || username.contentEquals("")) {
					
					authenticationToken = new UsernamePasswordAuthenticationToken("Username not entered", "",
							new ArrayList<>());
					return authenticationToken;
				}

				if (password == null || password.contentEquals("")) {

					authenticationToken = new UsernamePasswordAuthenticationToken("Password not entered", "",
							new ArrayList<>());
					return authenticationToken;
				}

				boolean userNameExists = this.loginService.checkIfUserExists(username);

				if (!userNameExists) {

					authenticationToken = new UsernamePasswordAuthenticationToken("Username does not exist", "",
							new ArrayList<>());
					return authenticationToken;
				} else {
					boolean authenticate = this.loginService.checkUser(username, password);
					System.out.println("loginService result:" + authenticate);
					if (authenticate) {
						Collection<? extends GrantedAuthority> authorities = Collections
								.singleton(new SimpleGrantedAuthority("ROLE_USER"));
						authenticationToken = new UsernamePasswordAuthenticationToken(username, password, authorities);
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
						return authenticationToken;
					} else {
						System.out.println("in #3");
//			    	throw new BadCredentialsException("Invalid Credentials");
						authenticationToken = new UsernamePasswordAuthenticationToken("Invalid Credentials", "",
								new ArrayList<>());
						return authenticationToken;
					}

				}

			} catch (Exception e) {

				throw new BadCredentialsException("Invalid Service Provider");
			}

		} catch (Exception e) {

			throw new BadCredentialsException("Invalid Service Provider");
		}

	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}

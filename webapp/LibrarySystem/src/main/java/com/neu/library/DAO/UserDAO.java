package com.neu.library.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.neu.library.model.User;

@Service
public class UserDAO {
	
	
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public User saveUser(User user) {
		return this.entityManager.merge(user);
}
	

}

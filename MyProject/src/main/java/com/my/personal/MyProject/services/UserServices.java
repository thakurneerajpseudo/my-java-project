package com.my.personal.MyProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.personal.MyProject.beans.UserDetails;
import com.my.personal.MyProject.beans.Users;
import com.my.personal.MyProject.repositories.UserDetailsRepository;
import com.my.personal.MyProject.repositories.UsersRepository;

@Service
public class UserServices {

	@Autowired
	private UsersRepository repository;

	@Autowired
	private UserDetailsRepository detailsRepository;
	
	
	public Users saveUser(Users user) {
		return repository.save(user);
	}

	public List<Users> findAllUser() {
		return repository.findAll();
	
	}

	public Users findByEmail(String email) {
		
		return repository.findByEmail(email);
	}

	public void saveUserDetails(UserDetails details) {
		detailsRepository.save(details);
	}
}

package com.meta.nexus.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.meta.nexus.models.LoginUser;
import com.meta.nexus.models.User;
import com.meta.nexus.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
		
		Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
		if(optionalUser.isPresent()) {
			result.rejectValue("email", "Unique", "The email is already registered");
		}
		
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Password and confirm password do not match");
		}
		
		if(result.hasErrors()) {
			return null;
		}
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		
		return userRepo.save(newUser);
	}
	
	public User login(LoginUser newLogin, BindingResult result) {
		Optional<User> optionalUser = userRepo.findByEmail(newLogin.getEmail());
		
	if(!optionalUser.isPresent()) {
		result.rejectValue("email", "Unique", "The email is not registered");
		return null;
	}
	User user = optionalUser.get();
	
	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
		result.rejectValue("password", "Matches", "Invalid Password");
	}
	if(result.hasErrors()) {
		return null;
	}
	return user;
	}
	
	public User oneUser(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();		
		} else {
			return null;
		}
	}

}

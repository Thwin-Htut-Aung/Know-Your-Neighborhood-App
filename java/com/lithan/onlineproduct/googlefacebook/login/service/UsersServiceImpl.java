package com.lithan.onlineproduct.googlefacebook.login.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lithan.onlineproduct.googlefacebook.login.dao.Users;
import com.lithan.onlineproduct.googlefacebook.login.exception.ResourceNotFoundException;
import com.lithan.onlineproduct.googlefacebook.login.repository.UsersRepository;

//UserDetailsService is the default built-in interface
/*
 * --- Service Class
--- implements UserDetailsService because want to use loadUserByUsername method (Override method)
--- To authenticate user based on user role (USER) because Spring Security needs to load user details. 
 */
@Service
@Transactional
public class UsersServiceImpl implements UserDetailsService{

	@Autowired
	private UsersRepository userRepo;
	
	//Override Method for load user by username 
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users users = userRepo.findByEmail(email)
		.orElseThrow( () -> new UsernameNotFoundException("This email cannot be found" + email));
		
		return UsersPrincipal.createUser(users);
	}
	
	//It is used for JWT Authentication Filter
	public UserDetails loadUserById(long userId){
		Users users = userRepo.findById(userId)
		.orElseThrow( () -> new ResourceNotFoundException("Users", "userId", userId));
		
		return UsersPrincipal.createUser(users);
	}

}

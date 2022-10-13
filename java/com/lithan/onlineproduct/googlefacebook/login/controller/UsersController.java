package com.lithan.onlineproduct.googlefacebook.login.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lithan.onlineproduct.googlefacebook.login.dao.Users;
import com.lithan.onlineproduct.googlefacebook.login.exception.ResourceNotFoundException;
import com.lithan.onlineproduct.googlefacebook.login.repository.UsersRepository;
import com.lithan.onlineproduct.googlefacebook.login.service.UsersPrincipal;

@RestController
@RequestMapping(value="/online")
public class UsersController {
	@Autowired
    private UsersRepository userRepository;

	//Profile API <<Get Current User Profile>>
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public Users getUser(@CurrentUser UsersPrincipal usersPrincipal) {
    	return userRepository.findById((usersPrincipal.getUserId())) 
                .orElseThrow(() -> new ResourceNotFoundException("Users", "userId", usersPrincipal.getUserId()));
    }
}

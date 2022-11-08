package com.lithan.onlineproduct.googlefacebook.login.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.lithan.onlineproduct.googlefacebook.login.dao.AuthProvider;
import com.lithan.onlineproduct.googlefacebook.login.dao.Users;
import com.lithan.onlineproduct.googlefacebook.login.exception.BadRequestException;
import com.lithan.onlineproduct.googlefacebook.login.jwtsecurity.TokenProvider;
import com.lithan.onlineproduct.googlefacebook.login.payload.Login;
import com.lithan.onlineproduct.googlefacebook.login.payload.LoginResponse;
import com.lithan.onlineproduct.googlefacebook.login.payload.Register;
import com.lithan.onlineproduct.googlefacebook.login.payload.RegisterResponse;
import com.lithan.onlineproduct.googlefacebook.login.repository.UsersRepository;

@RestController
@RequestMapping("/online")
public class AuthController {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;                     
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	//Register User for Local (not Google or Facebook signup)
	@PostMapping(value="/register")
	public ResponseEntity<?> registerUser(@RequestBody Register register){
		//Checking duplicate email
		if(usersRepository.existsByEmail(register.getEmail())) {
			throw new BadRequestException("Email has already registered before so kindly try anthoner email");
		}
		
		//Create new register user
		Users users = new Users();
		users.setUserName(register.getUserName());
		users.setEmail(register.getEmail());
		users.setPassword(register.getPassword());
		users.setProvider(AuthProvider.local);
		
		//Encode Password
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		
		//Save new user in the database
		Users newUser = usersRepository.save(users);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/user/me")
				.buildAndExpand(newUser.getUserId()).toUri();
		
		//Return to RegisterResponse Payload
		return ResponseEntity.created(location)
				.body(new RegisterResponse(true, "User has successfully registered!!!"));
	}
	
	//Local Login (not Google or Facebook)
	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Login login) {

		//Checking Authentication 
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		login.getEmail(),
                		login.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //If authorized user, create token
        String token = tokenProvider.createToken(authentication);
        
        //Return to LoginResponse Payload
        return ResponseEntity.ok(new LoginResponse(token));
    }
	
}

package com.vaibhavvasurkar.blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavvasurkar.blog.exceptions.ApiException;
import com.vaibhavvasurkar.blog.payloads.SigninRequest;
import com.vaibhavvasurkar.blog.payloads.SigninResponse;
import com.vaibhavvasurkar.blog.payloads.UserDTO;
import com.vaibhavvasurkar.blog.security.JwtUtils;
import com.vaibhavvasurkar.blog.services.*;

@RestController
@RequestMapping("/users")
public class UserSignInSignupController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authMgr;

	/*
	 * URL - http://host:port/users/signin Method - POST request payload : Auth req
	 * DTO : email n password resp payload : In case of success : Auth Resp DTO :
	 * mesg + JWT token + SC 201 In case of failure : SC 401
	 * 
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody 
			@Valid SigninRequest request)  {
		try {
			System.out.println("in sign in" + request);
			//create a token(implementation of Authentication i/f)
			//to store un verified user email n pwd
			UsernamePasswordAuthenticationToken token=new 
					UsernamePasswordAuthenticationToken(request.getEmail(), 
							request.getPassword());
			//invoke auth mgr's authenticate method;
			
			Authentication verifiedToken = authMgr.authenticate(token);
			
			
			//=> authentication n authorization  successful !
			System.out.println(verifiedToken.getPrincipal().getClass());//custom user details object
			//create JWT n send it to the clnt in response
			SigninResponse resp=new SigninResponse
					(jwtUtils.generateJwtToken(verifiedToken),
					"Successful Auth!!!!");
			return ResponseEntity.
					status(HttpStatus.CREATED).body(resp);
		} catch( BadCredentialsException e) {			
			throw new ApiException("Invalid username or password !!");
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> registerNewUser(@RequestBody UserDTO userDTO){
		UserDTO registeredNewUser = userService.registerNewUser(userDTO);
		return new ResponseEntity<UserDTO>(registeredNewUser, HttpStatus.CREATED);
	}

}

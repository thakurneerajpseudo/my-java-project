package com.my.personal.MyProject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.personal.MyProject.AuthResponse;
import com.my.personal.MyProject.JwtUtil;
import com.my.personal.MyProject.TokenBlacklist;
import com.my.personal.MyProject.beans.UserDetails;
import com.my.personal.MyProject.beans.Users;
import com.my.personal.MyProject.services.UserServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	UserServices services;
	@Autowired
	private TokenBlacklist tokenBlacklist;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody Users user) {
		String abc = encoder.encode(user.getPassword());
		user.setPassword(abc);
		services.saveUser(user);

		return ResponseEntity.ok(user);
	}

	@PostMapping("/users")
	public ResponseEntity<?> getAllUser() {
		List<Users> usres = services.findAllUser();
		return ResponseEntity.ok(usres);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Users authRequest, HttpSession session) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

			// Generate token using the JwtUtil instance
			String token;
			try {
				token = jwtUtil.generateToken(authentication); // Call on instance, not class
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(Map.of("message", "Error generating token"));
			}
            // Setting user in session 
			Users user = services.findByEmail(authRequest.getEmail());
	        if (user != null) {
	            session.setAttribute("loggedInUser", user);
	        }
			
			return ResponseEntity.ok(new AuthResponse(token));
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid email or password"));
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);
			tokenBlacklist.revoke(jwt);
		}
		
		  // Invalidate session to remove user
	    HttpSession session = request.getSession(false); 
	    if (session != null) {
	        session.invalidate(); 
	    }
		
		return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
	}
	
	//Now Add details of this user starts from here 
	
//	@PostMapping("/addUserDetails")
//	public ResponseEntity<?> addUserDetails(@RequestBody UserDetails details, HttpSession  request) {
//		
//		Users user = (Users) request.getAttribute("loggedInUser");
//		details.setUsers(user);
//		services.saveUserDetails(details);
//		
//
//		return ResponseEntity.ok(details);
//	}
	
	@PostMapping("/addUserDetails")
	public ResponseEntity<?> addUserDetails(@RequestBody UserDetails details, HttpSession session) {
	    System.out.println("Session ID: " + session.getId());
	    
	    Users user = (Users) session.getAttribute("loggedInUser");

	    System.out.println("Session user: " + user);

	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User not logged in"));
	    }

	    details.setUsers(user);
	    services.saveUserDetails(details);

	    return ResponseEntity.ok(details);
	}

	

}

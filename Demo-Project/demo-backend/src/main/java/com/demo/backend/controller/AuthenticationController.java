package com.demo.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.backend.dto.AuthRequest;
import com.demo.backend.dto.AuthResponse;
import com.demo.backend.dto.UserDTO;
import com.demo.backend.security.JwtTokenUtil;
import com.demo.backend.service.CustomUserService;

@CrossOrigin("*")
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserService customUserService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
	
		authenticate(authRequest.getEmailId(), authRequest.getPassword());

		final UserDetails userDetails = customUserService.loadUserByUsername(authRequest.getEmailId());

		final String token = jwtTokenUtil.generateToken(userDetails);
		AuthResponse authResponse = new AuthResponse(); 
		authResponse.setToken(token);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setName("Temp Name");
		userDTO.setEmail("g@admin.com");
		authResponse.setUser(userDTO);
		
		return ResponseEntity.ok(authResponse);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}

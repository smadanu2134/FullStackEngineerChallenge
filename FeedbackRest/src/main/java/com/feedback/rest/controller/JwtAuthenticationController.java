package com.feedback.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.dto.JwtRequest;
import com.feedback.dto.Response;
import com.feedback.dto.UserDTO;
import com.feedback.dto.UserDetailsDTO;
import com.feedback.security.JwtTokenUtil;
import com.feedback.service.impl.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/auth")
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<Response> authenticate(@RequestBody JwtRequest authenticationRequest) throws Exception {
		Response resp = new Response();
		ResponseEntity<Response> respEntity = null;
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			Map<String, Object> map = userDetailsService.loadActiveUser(authenticationRequest.getUsername());
			UserDetails user = (User) map.get("user");
			final String token = jwtTokenUtil.generateToken(user);
			UserDetailsDTO userDto = (UserDetailsDTO) map.get("userDetails");
			userDto.setJwtToken(token);
		
			resp.setCode(HttpStatus.OK.value());
			resp.setResponse(userDto);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.OK);
		} catch(Exception ex) {
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			List<String> err = new ArrayList<String>();
			err.add(ex.getMessage());
			resp.setErrors(err);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		
				
		return respEntity;
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
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

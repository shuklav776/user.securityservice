package com.buildown.user.securityservice.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buildown.user.securityservice.jwt.models.AuthenticationRequest;
import com.buildown.user.securityservice.jwt.models.AuthenticationResponse;
import com.buildown.user.securityservice.jwt.request.AuthRequest;
import com.buildown.user.securityservice.jwt.service.BuildownUserDetailService;
import com.buildown.user.securityservice.jwt.util.JwtUtil;

@RestController
public class BuildOwnSecurityController {

	@Autowired
	private BuildownUserDetailService buildownUserDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/ping")
	public String ping() {
		// authenticationManager.authenticate(
		// new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
		// authRequest.getPassword()));
		// UserDetails userDetails =
		// buildownUserDetailService.loadUserByUsername(authRequest.getUsername());
		return "You are sending token alon with request...";
	}

	@PostMapping("/generateToken")
	public ResponseEntity<?> generateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		System.out.println(authenticationRequest);

		try {

			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}

		UserDetails userDetails = buildownUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("token: " + token);
		return ResponseEntity.ok(new AuthenticationResponse(token));

	}
}

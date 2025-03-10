package org.shinesignbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.shinesignbackend.components.JwtTokenUtil;
import org.shinesignbackend.requests.LoginRequest;
import org.shinesignbackend.requests.RegisterRequest;
import org.shinesignbackend.requests.UpdateUserRequest;
import org.shinesignbackend.responses.LoginResponse;
import org.shinesignbackend.services.ShineSignUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ("/api/user")
@RequiredArgsConstructor
@Validated
public class ShineSignUserController {
	private final ShineSignUserService shineSignUserService;
	private final JwtTokenUtil jwtTokenUtil;

	@PostMapping ("/register")
	@ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<String> registerUser (@RequestBody @Valid RegisterRequest registerRequest) {
		this.shineSignUserService.register(registerRequest);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping ("/login")
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<LoginResponse> loginUser (@RequestBody @Valid LoginRequest loginRequest) {
		String token = this.shineSignUserService.login(loginRequest);
		return ResponseEntity.ok().body(new LoginResponse(token));
	}

	@PutMapping ("/update")
	@ResponseStatus (HttpStatus.OK)
	@PreAuthorize ("hasRole('ROLE_USER')")
	public ResponseEntity<String> updateUser (
		@RequestHeader("Authorization") String token,
		@RequestBody @Valid UpdateUserRequest updateUserRequest
	) {
		// remove "Bearer " from the token
		this.shineSignUserService.update(token.substring(7), updateUserRequest);
		return ResponseEntity.ok("User updated successfully");
	}
}
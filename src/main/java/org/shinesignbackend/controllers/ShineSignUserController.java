package org.shinesignbackend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.shinesignbackend.requests.LoginRequest;
import org.shinesignbackend.requests.RegisterRequest;
import org.shinesignbackend.requests.UpdateUserRequest;
import org.shinesignbackend.responses.LoginResponse;
import org.shinesignbackend.services.ShineSignUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/user")
@RequiredArgsConstructor
@Validated
public class ShineSignUserController {
	private final ShineSignUserService shineSignUserService;

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
		this.shineSignUserService.update(token, updateUserRequest);
		return ResponseEntity.ok("User updated successfully");
	}
}
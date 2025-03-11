package org.shinesignbackend.requests;

import jakarta.validation.constraints.Email;

public record RegisterRequest(
	@UsernamePasswordValidation String username,
	@UsernamePasswordValidation String password,
	@Email String email
) {
}
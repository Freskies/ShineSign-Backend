package org.shinesignbackend.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
	@NotBlank String username,
	@NotBlank String password,
	@Email String email
) {
}
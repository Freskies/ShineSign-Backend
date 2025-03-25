package org.shinesignbackend.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterRequest(
	@NotBlank
	@Pattern (
		regexp = "^[a-zA-Z0-9_-]*$",
		message = "username only allows alphanumeric characters, underscores, and hyphens"
	)
	String username,
	@NotBlank
	String password,
	@Email
	@NotBlank
	@NotNull
	String email
) {
}
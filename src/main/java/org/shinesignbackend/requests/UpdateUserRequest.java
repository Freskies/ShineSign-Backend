package org.shinesignbackend.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
	@NotBlank String password,
	@Email String email
) {
}
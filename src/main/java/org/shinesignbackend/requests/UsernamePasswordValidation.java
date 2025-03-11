package org.shinesignbackend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@NotBlank
@Pattern (
	regexp = "^[a-zA-Z0-9_-]*$",
	message = "username and password only allows alphanumeric characters, underscores, and hyphens"
)
public @interface UsernamePasswordValidation {
}

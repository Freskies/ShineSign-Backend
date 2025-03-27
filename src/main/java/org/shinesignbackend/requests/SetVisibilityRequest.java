package org.shinesignbackend.requests;

import jakarta.validation.constraints.NotNull;

public record SetVisibilityRequest(
	@NotNull Boolean visibility
) {
}

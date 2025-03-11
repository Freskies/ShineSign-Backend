package org.shinesignbackend.requests;

import jakarta.validation.constraints.NotBlank;

public record CreateDocumentRequest(
	@NotBlank String title
) {
}

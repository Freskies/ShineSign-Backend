package org.shinesignbackend.requests;

import org.jetbrains.annotations.NotNull;

public record CreateDocumentRequest(
	@NotNull String title
) {
}

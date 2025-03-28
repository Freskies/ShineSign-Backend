package org.shinesignbackend.responses;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.SignedDocument;

import java.util.UUID;

public record SignedDocumentResponse (
	UUID id,
	String signedDocumentUrl,
	String email
) {
	@Contract ("_ -> new")
	public static @NotNull SignedDocumentResponse fromSignedDocument (@NotNull SignedDocument signedDocument) {
		return new SignedDocumentResponse(
			signedDocument.getId(),
			signedDocument.getUrl(),
			signedDocument.getEmail()
		);
	}
}

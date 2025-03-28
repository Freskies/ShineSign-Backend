package org.shinesignbackend.responses;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.SignedDocument;

import java.util.List;

public record AllSignedDocumentResponse(
	List<SignedDocumentResponse> signedDocuments
) {
	@Contract ("_ -> new")
	public static @NotNull AllSignedDocumentResponse fromSignedDocuments (
		@NotNull List<SignedDocument> signedDocuments
	) {
		return new AllSignedDocumentResponse(
			signedDocuments.stream().map(SignedDocumentResponse::fromSignedDocument).toList()
		);
	}
}

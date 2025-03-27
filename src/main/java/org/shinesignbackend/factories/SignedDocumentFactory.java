package org.shinesignbackend.factories;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.SignedDocument;

public class SignedDocumentFactory {
	@Contract ("_, _ -> new")
	public static @NotNull SignedDocument createSignedDocument(String email, String url) {
		SignedDocument signedDocument = new SignedDocument();
		signedDocument.setEmail(email);
		signedDocument.setUrl(url);
		return signedDocument;
	}
}

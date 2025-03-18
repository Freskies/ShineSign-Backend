package org.shinesignbackend.responses;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Document;

import java.util.List;

public record AllDocumentsResponse(
	List<DocumentTitle> documents
) {
	@Contract ("_ -> new")
	public static @NotNull AllDocumentsResponse fromDocuments (@NotNull List<Document> documents) {
		return new AllDocumentsResponse(
			documents.stream()
				.map(DocumentTitle::fromDocument)
				.toList()
		);
	}
}

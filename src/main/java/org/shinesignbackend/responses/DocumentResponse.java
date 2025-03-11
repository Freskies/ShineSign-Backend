package org.shinesignbackend.responses;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Document;
import org.shinesignbackend.entities.Page;

import java.util.List;
import java.util.UUID;

public record DocumentResponse(
	UUID id,
	String title,
	List<Page> pages
) {
	@Contract ("_ -> new")
	public static @NotNull DocumentResponse fromDocument (@NotNull Document document) {
		return new DocumentResponse(
			document.getId(),
			document.getTitle(),
			document.getPages()
		);
	}
}

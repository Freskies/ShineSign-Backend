package org.shinesignbackend.responses;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Document;

import java.util.UUID;

public record DocumentTitle(
	UUID id,
	String title,
	Boolean isPublic
) {
	@Contract ("_ -> new")
	public static @NotNull DocumentTitle fromDocument (@NotNull Document document) {
		return new DocumentTitle(
			document.getId(),
			document.getTitle(),
			document.getIsPublic()
		);
	}
}

package org.shinesignbackend.factories;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Document;
import org.shinesignbackend.entities.ShineSignUser;

import java.util.List;

public class DocumentFactory {
	@Contract ("_, _ -> new")
	public static @NotNull Document createDocument (String title, ShineSignUser owner) {
		Document document = new Document();
		document.setTitle(title);
		document.setIsPublic(true);
		document.setPages(List.of(PageFactory.createFirstPage()));
		document.setOwner(owner);
		return document;
	}
}

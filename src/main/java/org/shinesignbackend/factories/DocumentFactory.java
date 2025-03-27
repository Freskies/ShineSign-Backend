package org.shinesignbackend.factories;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Document;
import org.shinesignbackend.entities.ShineSignUser;

import java.util.ArrayList;
import java.util.List;

public class DocumentFactory {
	@Contract ("_, _ -> new")
	public static @NotNull Document createDocument (String title, ShineSignUser owner) {
		Document document = new Document();
		document.setTitle(title);
		document.setOwner(owner);
		document.setIsPublic(false);
		document.setPages(List.of(PageFactory.createFirstPage()));
		document.setImages(new ArrayList<>() {});
		document.setSignedDocuments(new ArrayList<>() {});
		return document;
	}
}

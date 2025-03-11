package org.shinesignbackend.factories;

import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Document;
import org.shinesignbackend.entities.Page;

public class PageFactory {
	public static @NotNull Page createPage(int pageNumber) {
		Page page = new Page();
		page.setPageNumber(pageNumber);
		page.setStyle("");
		page.setBody("");
		return page;
	}

	public static @NotNull Page createFirstPage() {
		Page page = PageFactory.createPage(1);
		page.setBody("<!-- Start editing here -->");
		return page;
	}

	public static @NotNull Page createNextPage(@NotNull Document document) {
		return PageFactory.createPage(document.getPages().size() + 1);
	}
}

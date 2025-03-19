package org.shinesignbackend.factories;

import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Page;

public class PageFactory {
	public static @NotNull Page createFirstPage() {
		Page page = new Page();
		page.setIsFirst(true);
		page.setBody("<!-- Start editing here -->");
		page.setStyle("/* Write your style rules here */");
		page.setNextPage(null);
		return page;
	}

	public static @NotNull Page createPage() {
		Page page = new Page();
		page.setIsFirst(false);
		page.setBody("<!-- Start editing here -->");
		page.setStyle("/* Write your style rules here */");
		page.setNextPage(null);
		return page;
	}
}

package org.shinesignbackend.responses;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Page;

import java.util.List;

public record PageResponse(
	int pageNumber,
	String style,
	String body
) {
	@Contract ("_ -> new")
	public static @NotNull PageResponse fromPage (@NotNull Page page) {
		return new PageResponse(page.getPageNumber(), page.getStyle(), page.getBody());
	}

	public static List<PageResponse> fromPages (@NotNull List<Page> pages) {
		return pages.stream().map(PageResponse::fromPage).toList();
	}
}

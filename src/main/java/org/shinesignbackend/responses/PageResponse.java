package org.shinesignbackend.responses;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Page;

import java.util.List;
import java.util.UUID;

public record PageResponse(
	UUID id,
	boolean isFirst,
	String style,
	String body,
	UUID nextPage
) {
	@Contract ("_ -> new")
	public static @NotNull PageResponse fromPage (@NotNull Page page) {
		return new PageResponse(page.getId(), page.getIsFirst(), page.getStyle(), page.getBody(), page.getNextPage());
	}

	public static List<PageResponse> fromPages (@NotNull List<Page> pages) {
		return pages.stream().map(PageResponse::fromPage).toList();
	}
}

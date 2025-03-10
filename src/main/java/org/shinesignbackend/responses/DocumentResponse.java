package org.shinesignbackend.responses;

import org.shinesignbackend.entities.Page;

import java.util.List;
import java.util.UUID;

public record DocumentResponse(
	UUID id,
	String title,
	List<Page> pages
) {
}

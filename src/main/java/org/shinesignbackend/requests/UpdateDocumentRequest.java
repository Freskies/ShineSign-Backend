package org.shinesignbackend.requests;

import org.shinesignbackend.entities.Page;

import java.util.List;

public record UpdateDocumentRequest(
	String title,
	List<Page> pages
) {
}

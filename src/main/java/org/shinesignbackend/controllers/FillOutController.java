package org.shinesignbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.shinesignbackend.responses.AllDocumentsResponse;
import org.shinesignbackend.responses.DocumentResponse;
import org.shinesignbackend.services.DocumentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ("/api/fillOut")
@RequiredArgsConstructor
@Validated
public class FillOutController {
	private final DocumentService documentService;

	@GetMapping ("/{documentId}")
	public DocumentResponse getDocument (
		@PathVariable UUID documentId
	) {
		return this.documentService.getDocumentToFillOut(documentId);
	}
}

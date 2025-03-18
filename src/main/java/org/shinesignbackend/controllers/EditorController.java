package org.shinesignbackend.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.shinesignbackend.requests.CreateDocumentRequest;
import org.shinesignbackend.requests.UpdateDocumentRequest;
import org.shinesignbackend.responses.DocumentResponse;
import org.shinesignbackend.services.DocumentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ("/api/editor/document")
@RequiredArgsConstructor
@Validated
@PreAuthorize ("hasRole('ROLE_USER')")
public class EditorController {
	private final DocumentService documentService;

	@PostMapping
	public DocumentResponse createDocument (
		@RequestHeader ("Authorization") String token,
		@RequestBody @Valid @NotNull CreateDocumentRequest createDocumentRequest
	) {
		return this.documentService.createDocument(token, createDocumentRequest);
	}

	@GetMapping ("/{documentId}")
	public DocumentResponse getDocument (
		@RequestHeader ("Authorization") String token,
		@PathVariable UUID documentId
	) {
		return this.documentService.getDocument(token, documentId);
	}

	@PutMapping ("/{documentId}")
	public DocumentResponse updateDocument (
		@RequestHeader ("Authorization") String token,
		@PathVariable UUID documentId,
		@RequestBody @Valid @NotNull UpdateDocumentRequest updateDocumentRequest
	) {
		return this.documentService.updateDocument(token, documentId, updateDocumentRequest);
	}
}

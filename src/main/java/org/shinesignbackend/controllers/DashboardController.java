package org.shinesignbackend.controllers;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.shinesignbackend.responses.AllDocumentsResponse;
import org.shinesignbackend.responses.AllSignedDocumentResponse;
import org.shinesignbackend.services.DocumentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ("/api/dashboard")
@RequiredArgsConstructor
@Validated
@PreAuthorize ("hasRole('ROLE_USER')")
public class DashboardController {
	private final DocumentService documentService;

	@GetMapping("/documents")
	public AllDocumentsResponse getAllDocuments (
		@RequestHeader ("Authorization") String token
	) {
		return this.documentService.getAllDocuments(token);
	}

	@GetMapping("/{documentId}")
	public AllSignedDocumentResponse getAllSignedDocuments (
		@RequestHeader ("Authorization") String token,
		@PathVariable UUID documentId
	) {
		return this.documentService.getAllSignedDocuments(token, documentId);
	}
}

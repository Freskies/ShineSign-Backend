package org.shinesignbackend.controllers;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.shinesignbackend.responses.AllDocumentsResponse;
import org.shinesignbackend.services.DocumentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

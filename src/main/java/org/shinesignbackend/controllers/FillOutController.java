package org.shinesignbackend.controllers;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.shinesignbackend.responses.DocumentResponse;
import org.shinesignbackend.services.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping ("/api/fillOut")
@RequiredArgsConstructor
@Validated
public class FillOutController {
	private final DocumentService documentService;
	private final Cloudinary cloudinary;

	@GetMapping ("/{documentId}")
	public DocumentResponse getDocument (
		@PathVariable UUID documentId
	) {
		return this.documentService.getDocumentToFillOut(documentId);
	}

	@PostMapping (value = "/{documentId}/{email}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus (HttpStatus.CREATED)
	public void fillOutDocument (
		@PathVariable UUID documentId,
		@PathVariable String email,
		@RequestPart ("pdf") MultipartFile file
	) {
		try {
			var result = this.cloudinary.uploader().upload(
				file.getBytes(), Cloudinary.asMap(
					"folder",
					"ShineSignDocuments",
					"public_id",
					file.getOriginalFilename()
				)
			);
			String url = result.get("secure_url").toString();
			this.documentService.fillOutDocument(documentId, email, url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

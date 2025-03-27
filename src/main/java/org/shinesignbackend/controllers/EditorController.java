package org.shinesignbackend.controllers;

import com.cloudinary.Cloudinary;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.shinesignbackend.requests.CreateDocumentRequest;
import org.shinesignbackend.requests.SetVisibilityRequest;
import org.shinesignbackend.requests.UpdateDocumentRequest;
import org.shinesignbackend.responses.DocumentResponse;
import org.shinesignbackend.responses.ImageResponse;
import org.shinesignbackend.responses.ImagesResponse;
import org.shinesignbackend.responses.PageResponse;
import org.shinesignbackend.services.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping ("/api/editor/document")
@RequiredArgsConstructor
@Validated
@PreAuthorize ("hasRole('ROLE_USER')")
public class EditorController {
	private final DocumentService documentService;
	private final Cloudinary cloudinary;

	@PostMapping
	public DocumentResponse createDocument (
		@RequestHeader ("Authorization") String token,
		@RequestBody @Valid @NotNull CreateDocumentRequest createDocumentRequest
	) {
		return this.documentService.createDocument(token, createDocumentRequest);
	}

	@PostMapping ("/{documentId}/newPage")
	public PageResponse createPage (
		@RequestHeader ("Authorization") String token,
		@PathVariable UUID documentId
	) {
		return this.documentService.createPage(token, documentId);
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

	@PostMapping (value = "/{documentId}/newImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus (HttpStatus.CREATED)
	public ImageResponse uploadImage (
		@RequestHeader ("Authorization") String token,
		@PathVariable UUID documentId,
		@RequestPart ("file") MultipartFile file
	) {
		try {
			var result = this.cloudinary.uploader().upload(
				file.getBytes(), Cloudinary.asMap(
					"folder",
					"ShineSignImages",
					"public_id",
					file.getOriginalFilename()
				)
			);
			String url = result.get("secure_url").toString();
			return this.documentService.uploadImage(token, documentId ,url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping (value = "/{documentId}/allImages")
	public ImagesResponse getAllImages (
		@RequestHeader ("Authorization") String token,
		@PathVariable UUID documentId
	) {
		return this.documentService.getAllImages(token, documentId);
	}

	@DeleteMapping ("/{documentId}/deleteImage/{imageId}")
	public void deleteImage (
		@RequestHeader ("Authorization") String token,
		@PathVariable UUID documentId,
		@PathVariable UUID imageId
	) {
		this.documentService.deleteImage(token, documentId, imageId);
	}
}

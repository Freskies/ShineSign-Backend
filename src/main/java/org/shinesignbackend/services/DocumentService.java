package org.shinesignbackend.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Document;
import org.shinesignbackend.entities.Page;
import org.shinesignbackend.entities.UploadedImage;
import org.shinesignbackend.factories.DocumentFactory;
import org.shinesignbackend.factories.PageFactory;
import org.shinesignbackend.factories.UploadedImageFactory;
import org.shinesignbackend.repositories.DocumentRepository;
import org.shinesignbackend.repositories.UploadedImageRepository;
import org.shinesignbackend.requests.CreateDocumentRequest;
import org.shinesignbackend.requests.UpdateDocumentRequest;
import org.shinesignbackend.responses.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {
	private final ShineSignUserService shineSignUserService;
	private final DocumentRepository documentRepository;
	private final UploadedImageRepository uploadedImageRepository;

	// HELPER METHODS

	private @NotNull Document getDocumentFromId (UUID documentId) {
		return this.documentRepository.findById(documentId).orElseThrow(
			() -> new IllegalArgumentException("Document not found")
		);
	}

	private void checkDocumentOwner (String token, UUID documentId) {
		Document document = this.getDocumentFromId(documentId);
		if (!document.getOwner().getId().equals(this.shineSignUserService.getUserFromToken(token).getId()))
			throw new IllegalArgumentException("Document not found");
	}

	// PAGES METHODS

	public PageResponse createPage (String token, UUID documentId) {
		Document document = this.getDocumentFromId(documentId);
		this.checkDocumentOwner(token, documentId);
		Page page = PageFactory.createPage();
		document.getPages().add(page);
		Document newDocument = this.documentRepository.save(document);
		return PageResponse.fromPage(newDocument.getPages().get(document.getPages().size() - 1));
	}

	// IMAGES METHODS

	public ImageResponse uploadImage (String token, UUID documentId, String url) {
		Document document = this.getDocumentFromId(documentId);
		this.checkDocumentOwner(token, documentId);
		UploadedImage uploadedImage = UploadedImageFactory.createUploadedImage(url);
		document.getImages().add(this.uploadedImageRepository.save(uploadedImage));
		this.documentRepository.save(document);
		return ImageResponse.fromUploadedImage(uploadedImage);
	}

	public ImagesResponse getAllImages (String token, UUID documentId) {
		Document document = this.getDocumentFromId(documentId);
		this.checkDocumentOwner(token, documentId);
		List<UploadedImage> images = document.getImages();
		return ImagesResponse.fromUploadedImage(images);
	}

	public void deleteImage (String token, UUID documentId, UUID imageId) {
		Document document = this.getDocumentFromId(documentId);
		this.checkDocumentOwner(token, documentId);
		UploadedImage image = document.getImages().stream()
			.filter(i -> i.getId().equals(imageId)).findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Image not found"));
		document.getImages().remove(image);
		this.documentRepository.save(document);
		this.uploadedImageRepository.delete(image);
	}

	// DOCUMENT METHODS

	public DocumentResponse createDocument (String token, @NotNull CreateDocumentRequest createDocumentRequest) {
		Document document = DocumentFactory.createDocument(
			createDocumentRequest.title(),
			this.shineSignUserService.getUserFromToken(token)
		);
		this.documentRepository.save(document);
		return DocumentResponse.fromDocument(document);
	}

	public DocumentResponse getDocument (String token, UUID documentId) {
		Document document = this.getDocumentFromId(documentId);
		this.checkDocumentOwner(token, document.getId());
		return DocumentResponse.fromDocument(document);
	}

	public DocumentResponse getDocumentToFillOut (UUID documentId) {
		Document document = this.getDocumentFromId(documentId);
		if (!document.getIsPublic())
			throw new IllegalArgumentException("Document not found");
		return DocumentResponse.fromDocument(document);
	}

	public DocumentResponse updateDocument (
		String token,
		UUID documentId,
		@Valid @NotNull UpdateDocumentRequest updateDocumentRequest
	) {
		Document document = this.getDocumentFromId(documentId);
		this.checkDocumentOwner(token, document.getId());
		if (updateDocumentRequest.title() != null)
			document.setTitle(updateDocumentRequest.title());
		if (updateDocumentRequest.pages() != null)
			document.setPages(updateDocumentRequest.pages());
		this.documentRepository.save(document);
		return DocumentResponse.fromDocument(document);
	}

	public AllDocumentsResponse getAllDocuments (String token) {
		return new AllDocumentsResponse(
			this.documentRepository.findAllByOwner(this.shineSignUserService.getUserFromToken(token))
		);
	}
}

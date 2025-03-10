package org.shinesignbackend.services;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Document;
import org.shinesignbackend.repositories.DocumentRepository;
import org.shinesignbackend.requests.CreateDocumentRequest;
import org.shinesignbackend.responses.DocumentResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {
	private final PageService pageService;
	private final ShineSignUserService shineSignUserService;
	private final DocumentRepository documentRepository;

	@Contract ("_ -> new")
	private @NotNull DocumentResponse documentResponseFromDocument (@NotNull Document document) {
		return new DocumentResponse(
			document.getId(),
			document.getTitle(),
			document.getPages()
		);
	}

	public DocumentResponse createDocument (String token, CreateDocumentRequest createDocumentRequest) {
		Document document = new Document();
		BeanUtils.copyProperties(createDocumentRequest, document);
		document.setOwner(this.shineSignUserService.getUserFromToken(token));
		this.documentRepository.save(document);
		return this.documentResponseFromDocument(document);
	}

	public DocumentResponse getDocument (String token, UUID documentId) {
		Document document = this.documentRepository.findById(documentId).orElseThrow(
			() -> new IllegalArgumentException("Document not found")
		);
		if (!document.getOwner().getId().equals(this.shineSignUserService.getUserFromToken(token).getId())) {
			throw new IllegalArgumentException("Document not found");
		}
		return this.documentResponseFromDocument(document);
	}


}

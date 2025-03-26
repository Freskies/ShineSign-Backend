package org.shinesignbackend.repositories;

import org.shinesignbackend.entities.SignedDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SignedDocumentRepository extends JpaRepository<SignedDocument, UUID> {
}

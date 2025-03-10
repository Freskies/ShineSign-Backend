package org.shinesignbackend.repositories;

import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
	@NotNull Optional<Document> findById(@NotNull UUID id);
}

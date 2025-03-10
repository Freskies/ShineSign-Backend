package org.shinesignbackend.repositories;

import org.shinesignbackend.entities.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PageRepository extends JpaRepository<Page, UUID> {
}

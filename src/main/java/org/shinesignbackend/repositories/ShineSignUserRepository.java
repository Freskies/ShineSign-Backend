package org.shinesignbackend.repositories;

import org.shinesignbackend.entities.ShineSignUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShineSignUserRepository extends JpaRepository<ShineSignUser, UUID> {
	Optional<ShineSignUser> findByUsername (String username);

	boolean existsByUsername (String username);
}
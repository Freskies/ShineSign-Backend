package org.shinesignbackend.repositories;

import org.shinesignbackend.entities.UploadedImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UploadedImageRepository extends JpaRepository<UploadedImage, UUID> {
}
package org.shinesignbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table (name = "uploaded_images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadedImage {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (nullable = false)
	@JdbcTypeCode (SqlTypes.UUID)
	private UUID id;

	@Column (nullable = false)
	private String name;

	private String url;
}

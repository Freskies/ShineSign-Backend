package org.shinesignbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table (name = "signed_documents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignedDocument {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (nullable = false)
	@JdbcTypeCode (SqlTypes.UUID)
	private UUID id;

	@Column (nullable = false)
	private String url;

	private String email;
}

package org.shinesignbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "documents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (nullable = false)
	@JdbcTypeCode (SqlTypes.UUID)
	private UUID id;

	private String title;

	@OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Page> pages;

	@ElementCollection (fetch = FetchType.EAGER)
	private List<String> pdfUrls;

	@ManyToOne
	private ShineSignUser owner;
}
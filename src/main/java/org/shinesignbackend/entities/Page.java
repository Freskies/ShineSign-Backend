package org.shinesignbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table (name = "pages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (nullable = false)
	@JdbcTypeCode (SqlTypes.UUID)
	private UUID id;

	private int pageNumber;

	@Column (length = 65535)
	private String style;

	@Column (length = 65535)
	private String body;

//	@ManyToOne
//	private Document document;
}

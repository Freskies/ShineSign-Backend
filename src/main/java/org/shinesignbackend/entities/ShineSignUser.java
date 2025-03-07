package org.shinesignbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.shinesignbackend.configs.Role;

import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "shinesign_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShineSignUser {
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	@Column (nullable = false)
	@JdbcTypeCode (SqlTypes.UUID)
	private UUID id;

	@Column (nullable = false, unique = true)
	private String username;

	@Column (nullable = false)
	private String password;

	@Column (nullable = false)
	private String email;

//	@OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<Document> documents;

	@ElementCollection (fetch = FetchType.EAGER)
	@Enumerated (EnumType.STRING)
	private List<Role> roles;
}
package org.shinesignbackend.services;

import lombok.RequiredArgsConstructor;
import org.shinesignbackend.entities.ShineSignUser;
import org.shinesignbackend.repositories.ShineSignUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final ShineSignUserRepository shineSignUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ShineSignUser customer = shineSignUserRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));

		return new User(
			customer.getUsername(),
			customer.getPassword(),
			customer.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.name()))
				.collect(Collectors.toList())
		);
	}
}

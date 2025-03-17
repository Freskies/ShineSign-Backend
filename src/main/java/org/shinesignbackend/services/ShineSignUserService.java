package org.shinesignbackend.services;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.configs.Role;
import org.shinesignbackend.entities.ShineSignUser;
import org.shinesignbackend.components.JwtTokenUtil;
import org.shinesignbackend.factories.ShineSignUserFactory;
import org.shinesignbackend.repositories.ShineSignUserRepository;
import org.shinesignbackend.requests.LoginRequest;
import org.shinesignbackend.requests.RegisterRequest;
import org.shinesignbackend.requests.UpdateUserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShineSignUserService {
	private final ShineSignUserRepository shineSignUserRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;

	private ShineSignUser getUserByUsername (String username) {
		return this.shineSignUserRepository.findByUsername(username).orElseThrow(
			() -> new EntityExistsException(username)
		);
	}

	public ShineSignUser getUserFromToken (@NotNull String token) {
		String username = this.jwtTokenUtil.getUsernameFromToken(token.substring(7));
		return this.getUserByUsername(username);
	}

	public void register (@Valid @NotNull RegisterRequest registerRequest) {
		if (this.shineSignUserRepository.existsByUsername(registerRequest.username()))
			throw new EntityExistsException(registerRequest.username());
		ShineSignUser shineSignUser = ShineSignUserFactory.fromRegisterRequest(registerRequest, this.passwordEncoder);
		shineSignUser.setRoles(List.of(Role.ROLE_USER));
		this.shineSignUserRepository.save(shineSignUser);
	}

	public String login (@Valid @NotNull LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
		);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return this.jwtTokenUtil.generateToken(userDetails);
	}

	public void update (@NotNull String token, @Valid UpdateUserRequest updateUserRequest) {
		ShineSignUser shineSignUser = this.getUserFromToken(token);
		BeanUtils.copyProperties(updateUserRequest, shineSignUser);
		this.shineSignUserRepository.save(shineSignUser);
	}

	public boolean isValidToken (@NotNull String username, @NotNull String token) {
		try {
			String tokenUsername = this.getUserFromToken(token).getUsername();
			return username.equals(tokenUsername);
		} catch (MalformedJwtException | UsernameNotFoundException e) {
			return false;
		}
	}
}
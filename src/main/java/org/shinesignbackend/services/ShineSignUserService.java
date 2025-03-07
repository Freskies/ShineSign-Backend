package org.shinesignbackend.services;

import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.configs.Role;
import org.shinesignbackend.entities.ShineSignUser;
import org.shinesignbackend.components.JwtTokenUtil;
import org.shinesignbackend.repositories.ShineSignUserRepository;
import org.shinesignbackend.requests.LoginRequest;
import org.shinesignbackend.requests.RegisterRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

	private @NotNull ShineSignUser shineSignUserFromRegisterRequest (@Valid RegisterRequest registerRequest) {
		ShineSignUser shineSignUser = new ShineSignUser();
		BeanUtils.copyProperties(registerRequest, shineSignUser);
		shineSignUser.setPassword(passwordEncoder.encode(registerRequest.password()));
		return shineSignUser;
	}

	public void register (@Valid @NotNull RegisterRequest registerRequest) {
		if (this.shineSignUserRepository.existsByUsername(registerRequest.username()))
			throw new EntityExistsException(registerRequest.username());
		ShineSignUser shineSignUser = this.shineSignUserFromRegisterRequest(registerRequest);
		shineSignUser.setRoles(List.of(Role.ROLE_USER));
		this.shineSignUserRepository.save(shineSignUser);
	}

	public String login (@Valid @NotNull LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
		);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return jwtTokenUtil.generateToken(userDetails);
	}
}
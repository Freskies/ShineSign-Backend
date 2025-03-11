package org.shinesignbackend.factories;

import org.jetbrains.annotations.NotNull;
import org.shinesignbackend.entities.ShineSignUser;
import org.shinesignbackend.requests.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ShineSignUserFactory {
	public static @NotNull ShineSignUser fromRegisterRequest (
		@NotNull RegisterRequest registerRequest,
		@NotNull PasswordEncoder passwordEncoder
	) {
		ShineSignUser shineSignUser = new ShineSignUser();
		shineSignUser.setUsername(registerRequest.username());
		shineSignUser.setPassword(passwordEncoder.encode(registerRequest.password()));
		shineSignUser.setEmail(registerRequest.email());
		return shineSignUser;
	}
}

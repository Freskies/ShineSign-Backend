package org.shinesignbackend.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
	@Value ("${jwt.secret}")
	private String secret;

	@Value ("${jwt.expiration}")
	private long expiration;

	public Claims getAllClaimsFromToken (String token) {
		return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
	}

	public <T> T getClaimFromToken (String token, @NotNull Function<Claims, T> claimsResolver) {
		final Claims claims = this.getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public String getUsernameFromToken (String token) {
		return this.getClaimFromToken(token, Claims::getSubject);
	}

	private Date getExpirationDateFromToken (String token) {
		return this.getClaimFromToken(token, Claims::getExpiration);
	}

	private boolean isTokenExpired (String token) {
		final Date expiration = this.getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken (@NotNull UserDetails userDetails) {
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		List<String> roles = authorities.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.toList());

		return Jwts.builder()
			.setSubject(userDetails.getUsername())
			.claim("roles", roles)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + this.expiration))
			.signWith(SignatureAlgorithm.HS256, secret)
			.compact();
	}

	public boolean validateToken (String jwtToken, @NotNull UserDetails userDetails) {
		final String username = this.getUsernameFromToken(jwtToken);
		return username.equals(userDetails.getUsername()) && !this.isTokenExpired(jwtToken);
	}
}
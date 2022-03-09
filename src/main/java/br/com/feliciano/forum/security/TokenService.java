package br.com.feliciano.forum.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.feliciano.forum.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expiration;

	@Value("${forum.jwt.secret}")
	private String secretKey;

	public String generateToken(Authentication auth) {

		User userLogged = (User) auth.getPrincipal();
		Date today = new Date();
		Date expDate = new Date(today.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("API Forum").setSubject(userLogged.getId().toString()).setIssuedAt(today)
				.setExpiration(expDate).signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	public boolean isTokenValid(String token) {

		try {
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}

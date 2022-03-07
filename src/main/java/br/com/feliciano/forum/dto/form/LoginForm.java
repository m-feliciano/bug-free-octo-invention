package br.com.feliciano.forum.dto.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginForm {

	private String email;
	private String password;

	public UsernamePasswordAuthenticationToken conterver() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}

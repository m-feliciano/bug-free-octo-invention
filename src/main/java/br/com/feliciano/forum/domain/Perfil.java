package br.com.feliciano.forum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "tb_perfis")
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@Setter(AccessLevel.NONE)
	private Long id;

	private String name;

	public Perfil(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}
}

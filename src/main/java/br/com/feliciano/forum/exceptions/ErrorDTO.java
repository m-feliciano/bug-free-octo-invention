package br.com.feliciano.forum.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String camp;
	private String error;
}

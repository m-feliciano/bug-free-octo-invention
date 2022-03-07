package br.com.feliciano.forum.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ErrorModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
	private LocalDateTime timestamp;
	private String message;
	private String details;

	public ErrorModel(HttpStatus httpStatus, String message, String details) {
		this.httpStatus = httpStatus;
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.details = details;
	}

}

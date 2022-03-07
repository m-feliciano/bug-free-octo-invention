package br.com.feliciano.forum.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.feliciano.forum.domain.Answer;
import lombok.Getter;

@Getter

public class AnswerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private LocalDateTime localDateTime;
	private String authorName;

	public AnswerDTO(Answer answer) {
		this.id = answer.getId();
		this.localDateTime = answer.getCreatedDate();
		this.authorName = answer.getAuthor().getName();
	}
}

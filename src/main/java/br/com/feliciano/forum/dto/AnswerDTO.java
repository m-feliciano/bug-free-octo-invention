package br.com.feliciano.forum.dto;

import br.com.feliciano.forum.domain.Answer;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter

public class AnswerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final long id;
    private final LocalDateTime localDateTime;
    private final String authorName;

    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.localDateTime = answer.getCreatedDate();
        this.authorName = answer.getAuthor().getName();
    }
}

package br.com.feliciano.forum.dto;

import br.com.feliciano.forum.domain.Answer;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AnswerDTO implements Serializable {

    private long id;
    private LocalDateTime localDateTime;
    private String authorName;

    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.localDateTime = answer.getCreatedDate();
        this.authorName = answer.getAuthor().getName();
    }
}

package br.com.feliciano.forum.dto;

import br.com.feliciano.forum.domain.Topic;
import br.com.feliciano.forum.domain.enums.StatusTopic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class TopicDetailsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String title;
    private String message;
    private LocalDateTime localDateTime;
    private String authorName;
    private StatusTopic statusTopic;
    private List<AnswerDTO> answers = new ArrayList<>();

    public TopicDetailsDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.localDateTime = topic.getCreatedDate();
        this.authorName = topic.getAuthor().getName();
        this.statusTopic = topic.getStatus();
        this.answers = topic.getAnswers().stream().map(AnswerDTO::new).collect(Collectors.toList());
    }

    public static List<TopicDetailsDTO> converter(List<Topic> topics) {
        return topics.stream().map(TopicDetailsDTO::new).collect(Collectors.toList());
    }

}

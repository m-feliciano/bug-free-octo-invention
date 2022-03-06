package br.com.feliciano.forum.dto;

import br.com.feliciano.forum.domain.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor

public class TopicDTO implements Serializable {

    private long id;
    private String title;
    private String message;
    private LocalDateTime localDateTime;

    public TopicDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.localDateTime = topic.getCreatedDate();
    }

    public static List<TopicDTO> converter(List<Topic> topics) {
        return topics
                .stream()
                .map(TopicDTO::new)
                .collect(Collectors.toList());
    }

}

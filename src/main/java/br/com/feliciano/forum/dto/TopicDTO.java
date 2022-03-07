package br.com.feliciano.forum.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.feliciano.forum.domain.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class TopicDTO implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public static Page<TopicDTO> converter(Page<Topic> topics) {
		return topics.map(TopicDTO::new);
	}

}

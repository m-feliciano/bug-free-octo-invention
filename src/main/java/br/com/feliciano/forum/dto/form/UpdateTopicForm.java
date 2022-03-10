package br.com.feliciano.forum.dto.form;

import br.com.feliciano.forum.domain.Topic;
import br.com.feliciano.forum.repository.TopicRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateTopicForm {

    @NotNull
    @NotEmpty(message = "Title must not be empty")
    @Length(min = 10, max = 50)
    private String title;
    @NotNull
    @NotEmpty(message = "Message must not be empty")
    @Length(min = 15, max = 144)
    private String message;

    public Topic update(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.findById(id).get();
        topic.setTitle(title);
        topic.setMessage(message);
        return topic;
    }
}

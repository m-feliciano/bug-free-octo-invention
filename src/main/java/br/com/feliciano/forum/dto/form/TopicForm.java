package br.com.feliciano.forum.dto.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.feliciano.forum.domain.Course;
import br.com.feliciano.forum.domain.Topic;
import br.com.feliciano.forum.domain.User;
import br.com.feliciano.forum.repository.CourseRepository;
import br.com.feliciano.forum.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TopicForm {

	@NotNull
	@NotEmpty(message = "Title must not be empty")
	@Length(min = 10, max = 50)
	private String title;
	@NotNull
	@NotEmpty(message = "Message must not be empty")
	@Length(min = 15, max = 144)
	private String message;
	@NotNull
	@NotEmpty(message = "Course's name must not be empty")
	@Length(min = 3, max = 50)
	private String courseName;
	@NotNull
	private Long author_id;

	public Topic converter(CourseRepository repository, UserRepository authorRepository) {
		Course course = repository.findByName(courseName);
		Optional<User> author = authorRepository.findById(author_id);
		return new Topic(title, message, author.get(), course);
	}
}

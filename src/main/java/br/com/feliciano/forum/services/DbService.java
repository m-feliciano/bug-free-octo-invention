package br.com.feliciano.forum.services;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.feliciano.forum.domain.Course;
import br.com.feliciano.forum.domain.Topic;
import br.com.feliciano.forum.domain.User;
import br.com.feliciano.forum.repository.CourseRepository;
import br.com.feliciano.forum.repository.TopicRepository;
import br.com.feliciano.forum.repository.UserRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Service
public class DbService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

//    @Autowired
//    private AnswerRepository answerRepository;

	public void instantiateDatabase() {

		User user1 = new User("ana", "ana@email.com", encoder("ana123"));
		User user2 = new User("bia", "bia@email.com", encoder("bia123"));

		Course course1 = new Course("IAC", "DevOps");
		Course course2 = new Course("AWS", "DevOps");
		Course course3 = new Course("Java EE", "Developer");
		Course course4 = new Course("Java JDBC", "Developer");
		Course course5 = new Course("Java Web", "Developer");

		Topic t1 = new Topic("Console log", "Cannot update that using cmd", user1, course1);
		t1.setCreatedDate(LocalDateTime.now().minusDays(1));
		Topic t2 = new Topic("AWS login", "Unable to access private user key", user2, course2);
		t2.setCreatedDate(LocalDateTime.now().minusDays(2));
		Topic t3 = new Topic("Java Reflection Class", "Can't load the class using reflection", user1, course3);
		t3.setCreatedDate(LocalDateTime.now().minusDays(3));
		Topic t4 = new Topic("Java JDBC", "link localhost:3306 not working", user2, course4);
		t4.setCreatedDate(LocalDateTime.now().minusDays(4));
		Topic t5 = new Topic("Java WEB API", "Can't load the class using reflection", user1, course5);
		t5.setCreatedDate(LocalDateTime.now().minusDays(5));

		userRepository.saveAll(Arrays.asList(user1, user2));
		courseRepository.saveAll(Arrays.asList(course1, course2, course3, course4, course5));
		topicRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));

	}

	private String encoder(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}

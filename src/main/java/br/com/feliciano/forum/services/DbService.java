package br.com.feliciano.forum.services;

import br.com.feliciano.forum.domain.Course;
import br.com.feliciano.forum.domain.Topic;
import br.com.feliciano.forum.domain.User;
import br.com.feliciano.forum.repository.AnswerRepository;
import br.com.feliciano.forum.repository.CourseRepository;
import br.com.feliciano.forum.repository.TopicRepository;
import br.com.feliciano.forum.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@NoArgsConstructor

@Service
public class DbService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public void instantiateDatabase() {

        User user1 = new User("ana", "ana@ana", "ana123");
        User user2 = new User("bia", "bia@bia", "bia123");

        Course course1 = new Course("Infrastructure as code", "DevOps");
        Course course2 = new Course("AWS", "DevOps");
        Course course3 = new Course("JavaEE", "Developer");

        Topic t1 = new Topic("Console log", "Cannot update that using cmd", LocalDateTime.now(), user1, course1);
        Topic t2 = new Topic("AWS login", "Unable to access private user key", LocalDateTime.now(), user2, course2);
        Topic t3 = new Topic("Java Reflection Class", "Can't load the class using reflection", LocalDateTime.now(), user1, course3);

        userRepository.saveAll(Arrays.asList(user1,user2));
        courseRepository.saveAll(Arrays.asList(course1, course2, course3));
        topicRepository.saveAll(Arrays.asList(t1, t2, t3));

    }
}

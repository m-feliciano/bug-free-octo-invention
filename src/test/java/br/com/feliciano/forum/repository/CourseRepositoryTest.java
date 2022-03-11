package br.com.feliciano.forum.repository;

import br.com.feliciano.forum.domain.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// prevent to replace / rewrite database configs
@ActiveProfiles("test") //force profile
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    void mustLoadACourseByName() {
        String name = "Java EE";
        Course course = repository.findByName(name);
        Assertions.assertEquals(name, course.getName());
        Assertions.assertNotNull(course, "Must be not null");
    }

    @Test
    void courseMustBeNull() {
        Course courseName = repository.findByName("Java EE30sq");
        Assertions.assertNull(courseName, "Must be null: course not exists");
    }

}
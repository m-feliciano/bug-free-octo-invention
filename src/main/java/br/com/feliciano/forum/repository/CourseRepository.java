package br.com.feliciano.forum.repository;

import br.com.feliciano.forum.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

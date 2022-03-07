package br.com.feliciano.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.feliciano.forum.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByName(String string);
}

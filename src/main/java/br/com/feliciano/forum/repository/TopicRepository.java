package br.com.feliciano.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.feliciano.forum.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	/*
	 * The _ remove any ambiguous that may occur between a variable courseName and
	 * the attribute name of entity Course.
	 */
	Page<Topic> findAllByCourse_Name(String courseName, Pageable pageable);
}

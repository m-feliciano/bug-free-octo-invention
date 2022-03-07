package br.com.feliciano.forum.repository;

import br.com.feliciano.forum.domain.Course;
import br.com.feliciano.forum.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    /* The _ remove any ambiguous that may occur between a variable
     * courseName and the attribute name of entity Course.
     */
    Page<Topic> findAllByCourse_Name(String courseName, Pageable pageable);
}

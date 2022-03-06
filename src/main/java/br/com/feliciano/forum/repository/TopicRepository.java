package br.com.feliciano.forum.repository;

import br.com.feliciano.forum.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}

package br.com.feliciano.forum.repository;

import br.com.feliciano.forum.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}

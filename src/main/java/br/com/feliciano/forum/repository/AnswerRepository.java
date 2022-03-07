package br.com.feliciano.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.feliciano.forum.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}

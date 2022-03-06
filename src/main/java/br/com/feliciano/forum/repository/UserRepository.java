package br.com.feliciano.forum.repository;

import br.com.feliciano.forum.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package br.com.feliciano.forum.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "tb_answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	private String message;

	@ManyToOne(fetch = FetchType.LAZY)
	private Topic topic;

	private LocalDateTime createdDate = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;
	private Boolean resolved = false;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Answer answer = (Answer) o;
		return Objects.equals(id, answer.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

package br.com.feliciano.forum.domain;

import br.com.feliciano.forum.domain.enums.StatusTopic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString

@Entity
@Table(name = "tb_topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime createdDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopic status = StatusTopic.NOT_ANSWERED;

    @ManyToOne
    private User author;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers = new ArrayList<>();

    public Topic() {
        this.createdDate = LocalDateTime.now();
        this.status = StatusTopic.NOT_ANSWERED;
    }

    public Topic(String title, String message, Course course) {
        this.title = title;
        this.message = message;
        this.course = course;
        this.createdDate = LocalDateTime.now();
        this.status = StatusTopic.NOT_ANSWERED;
    }

    public Topic(String title, String message, User author, Course course) {
        this.title = title;
        this.message = message;
        this.author = author;
        this.course = course;
        this.createdDate = LocalDateTime.now();
        this.status = StatusTopic.NOT_ANSWERED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

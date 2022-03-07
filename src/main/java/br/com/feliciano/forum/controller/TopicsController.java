package br.com.feliciano.forum.controller;

import br.com.feliciano.forum.domain.Topic;
import br.com.feliciano.forum.dto.TopicDTO;
import br.com.feliciano.forum.dto.TopicDetailsDTO;
import br.com.feliciano.forum.dto.form.TopicForm;
import br.com.feliciano.forum.repository.CourseRepository;
import br.com.feliciano.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<TopicDTO> topics() {
        return TopicDTO.converter(topicRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public TopicDetailsDTO topic(@PathVariable("id") Long id) {
        Topic topic = topicRepository.getById(id);
        return new TopicDetailsDTO(topic);
    }

    @GetMapping(value = "/course/{name}")
    public List<TopicDTO> topicByCourseName(@PathVariable("name") String courseName) {
        return TopicDTO.converter(topicRepository.findAllByCourse_Name(courseName));
    }

    @PostMapping
    public ResponseEntity<TopicDTO> save(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder) {
        Topic topic = topicForm.converter(courseRepository);
        topicRepository.save(topic);
        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }
}

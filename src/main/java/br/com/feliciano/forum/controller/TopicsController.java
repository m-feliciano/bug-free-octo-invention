package br.com.feliciano.forum.controller;

import br.com.feliciano.forum.domain.Topic;
import br.com.feliciano.forum.dto.TopicDTO;
import br.com.feliciano.forum.dto.TopicDetailsDTO;
import br.com.feliciano.forum.dto.form.TopicForm;
import br.com.feliciano.forum.dto.form.UpdateTopicForm;
import br.com.feliciano.forum.repository.CourseRepository;
import br.com.feliciano.forum.repository.TopicRepository;
import br.com.feliciano.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Page<TopicDTO> topics(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "quantity", required = false, defaultValue = "10") Integer qtd
    ) {
        Pageable pageable = PageRequest.of(page, qtd, Sort.by("id").descending());
        return TopicDTO.converter(topicRepository.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TopicDetailsDTO> getTopicById(@PathVariable("id") Long id) {
        Topic topic = topicRepository.getById(id);
        return ResponseEntity.ok(new TopicDetailsDTO(topic));
    }

    @GetMapping(value = "/course/{name}")
    public Page<TopicDTO> getTopicByCourseName(
            @PathVariable(value = "name") String courseName,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "quantity", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return TopicDTO.converter(topicRepository.findAllByCourse_Name(courseName, pageable));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDTO> save(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder) {
        Topic topic = topicForm.converter(courseRepository, userRepository);
        topicRepository.save(topic);
        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDTO> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateTopicForm updateTopicForm) {
        Topic topic = updateTopicForm.update(id, topicRepository);
        return ResponseEntity.ok(new TopicDTO(topic));
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity remove(@PathVariable("id") Long id) {
        topicRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

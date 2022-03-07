package br.com.feliciano.forum.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.feliciano.forum.domain.Topic;
import br.com.feliciano.forum.dto.TopicDTO;
import br.com.feliciano.forum.dto.TopicDetailsDTO;
import br.com.feliciano.forum.dto.form.TopicForm;
import br.com.feliciano.forum.dto.form.UpdateTopicForm;
import br.com.feliciano.forum.repository.CourseRepository;
import br.com.feliciano.forum.repository.TopicRepository;
import br.com.feliciano.forum.repository.UserRepository;

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
	public Page<TopicDTO> topics(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		return TopicDTO.converter(topicRepository.findAll(pageable));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TopicDetailsDTO> getTopicById(@PathVariable("id") Long id) {
		Topic topic = topicRepository.getById(id);
		return ResponseEntity.ok(new TopicDetailsDTO(topic));
	}

	// Pagable or @RequestParam(value = "page", required = false) Integer
	// customNames
	@GetMapping(value = "/course")
	@Cacheable(value = "topicByCourseName")
	public Page<TopicDTO> getTopicByCourseName(@RequestParam(value = "name") String courseName,
			@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		return TopicDTO.converter(topicRepository.findAllByCourse_Name(courseName, pageable));
	}

	@PostMapping
	@Transactional // inform the spring to commit changes
	@CacheEvict(value = "topicByCourseName", allEntries = true) // refresh cache when this method is called
	public ResponseEntity<TopicDTO> save(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder) {
		Topic topic = topicForm.converter(courseRepository, userRepository);
		topicRepository.save(topic);
		URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDTO(topic));
	}

	@PutMapping("/{id}")
	@Transactional // inform the spring to commit changes
	@CacheEvict(value = "topicByCourseName", allEntries = true) // refresh cache when this method is called
	public ResponseEntity<TopicDTO> update(@PathVariable("id") Long id,
			@RequestBody @Valid UpdateTopicForm updateTopicForm) {
		Topic topic = updateTopicForm.update(id, topicRepository);
		return ResponseEntity.ok(new TopicDTO(topic));
	}

	@DeleteMapping(value = "/{id}")
	@Transactional // inform the spring to commit changes
	@CacheEvict(value = "topicByCourseName", allEntries = true) // refresh cache when this method is called
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		topicRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}

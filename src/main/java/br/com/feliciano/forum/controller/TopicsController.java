package br.com.feliciano.forum.controller;

import br.com.feliciano.forum.dto.TopicDTO;
import br.com.feliciano.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public List<TopicDTO> topics() {
        return TopicDTO.converter(topicRepository.findAll());
    }

    @GetMapping("/{id}")
    public Optional<TopicDTO> topic(@PathVariable("id") Long id) {
        return topicRepository.findById(id).map(TopicDTO::new);
    }
}

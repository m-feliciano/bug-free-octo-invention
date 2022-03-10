package br.com.feliciano.forum.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/")
public class DefaultController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping
    public String sayHello() {
        return "Hello world from " + appName + "!";
    }
}

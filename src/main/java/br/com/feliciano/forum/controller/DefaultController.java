package br.com.feliciano.forum.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class DefaultController {

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/")
    public String sayHello() {
        return "Hello world from " + appName + "!";
    }
}

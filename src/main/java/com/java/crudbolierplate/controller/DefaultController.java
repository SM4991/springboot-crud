package com.java.crudbolierplate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class DefaultController {
    @GetMapping("/")
    public ModelAndView helloWorld() {
        return new ModelAndView("index");
    }
}

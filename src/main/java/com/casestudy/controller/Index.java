package com.casestudy.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("")
public class Index {

    @GetMapping
    public static void main(String[] args) {
        System.out.print("Hello World");
    }
}

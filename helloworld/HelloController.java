package com.generation.hellowworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
	@GetMapping
	public String getHello () {
		return "Hello turma 44!";
	}
	
	@GetMapping("/turma44")
	public String getHello2 () {
		return "Hello Turma 44 é TOP!";
	}
}

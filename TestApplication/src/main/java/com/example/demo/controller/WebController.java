package com.example.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
public class WebController {

	
	@GetMapping("/home")
	public String homepage(){
		return "index.html";
	}
}

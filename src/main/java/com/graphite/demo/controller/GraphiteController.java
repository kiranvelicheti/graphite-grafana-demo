package com.graphite.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphite.demo.config.Timed;

@RestController
@RequestMapping("/api")
public class GraphiteController {

	@GetMapping("/graphite-demo")
	@Timed
	public String demoGraphite() {

		return "Hi!!! i'm a demo application for graphite grafana demo";
	}
}

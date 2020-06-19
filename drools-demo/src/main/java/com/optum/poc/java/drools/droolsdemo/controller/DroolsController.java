package com.optum.poc.java.drools.droolsdemo.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.optum.poc.java.drools.droolsdemo.model.Insurence;

@Controller
@RequestMapping("/drools")
public class DroolsController {
	
	@Autowired
	KieSession session;

	@PostMapping("/premium")
	public Insurence getPremium(@RequestBody Insurence insurence) {
		
		session.insert(insurence);
		session.fireAllRules();

		return insurence;
	}
}

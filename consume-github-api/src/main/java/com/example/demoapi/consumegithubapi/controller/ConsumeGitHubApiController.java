package com.example.demoapi.consumegithubapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demoapi.consumegithubapi.model.UserDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class ConsumeGitHubApiController {

	@Autowired
	RestTemplate rest;

	@GetMapping
	public String getApiResult() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		ResponseEntity<Object> apiResult = rest.exchange("https://api.github.com/users/joshuadeguzman",
				HttpMethod.GET, null, new ParameterizedTypeReference<Object>() {
				});

		UserDetails result = mapper.convertValue(apiResult.getBody(), new TypeReference<UserDetails>() {
		});

		return result.toString();
	}

}

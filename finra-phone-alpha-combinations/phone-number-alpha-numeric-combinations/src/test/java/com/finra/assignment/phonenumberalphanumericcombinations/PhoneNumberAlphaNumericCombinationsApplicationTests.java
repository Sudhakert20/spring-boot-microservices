package com.finra.assignment.phonenumberalphanumericcombinations;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.finra.assignment.phonenumberalphanumericcombinations.controller.PhoneNumberAlphaNumericCombinationsController;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class PhoneNumberAlphaNumericCombinationsApplicationTests {
	
	@LocalServerPort
	int port;

	@Autowired
	PhoneNumberAlphaNumericCombinationsController controller;

	@Autowired
	TestRestTemplate rest;

	@Test
	void contextLoads() {
		Assertions.assertThat(controller).isNotNull();
	}

	@Test
	void testFinra() {
		String url = "http://localhost:" + port + "/finra";
		String restObject = this.rest.getForObject(url, String.class);
		Assertions.assertThat(restObject).contains("Hello FINRA");
	}

}

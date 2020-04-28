package com.altimetric.worldbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.altimetric.worldbank.model.CountryWithCapitalCity;
import com.altimetric.worldbank.model.Worldbank;
import com.altimetric.worldbank.service.WorldbankService;

@RunWith(SpringRunner.class)
@SpringBootTest
class WorldbankApplicationTests {

	@Autowired
	WorldbankService service;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCountryByName() {
		Worldbank country = service.getWorldbankCountryByName("Aruba");

		assertTrue(country != null);

		assertEquals("Aruba", country.getName(), "country found");

	}

	@Test
	public void testCountryWithCapitalCity() {
		List<CountryWithCapitalCity> country =  service.getWorldBankCountriesWithCapitalCity("Aruba", true);
		
		assertTrue(!country.isEmpty());
		
		boolean isPresent = true;
		
		isPresent = country.stream().anyMatch(c -> c.getCountryName().equalsIgnoreCase("Aruba"));
		
		assertTrue(!isPresent);
		
	}

}

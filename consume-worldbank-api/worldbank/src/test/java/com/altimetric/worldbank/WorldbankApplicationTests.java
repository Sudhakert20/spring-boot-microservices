package com.altimetric.worldbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.altimetric.worldbank.model.Worldbank;
import com.altimetric.worldbank.service.WorldbankService;

@SpringBootTest
class WorldbankApplicationTests {

	@Autowired
	WorldbankService service;

	@Test
	public void testTotalCountries() {
		int totalCountries = service.getAllWorldbankDetails().size();
		assertEquals(304, totalCountries);
	}

	@Test
	public void testCountryByName() {
		Worldbank country = service.getWorldbankCountryByName("Aruba");

		assertTrue(country != null);

		assertEquals("Aruba", country.getName(), "country found");

	}

	@Test
	public void testCountryWithCapitalCity() {
		List<Worldbank> country = service.getWorldBankCountriesWithCapitalCity("Aruba", true, false, false);

		assertTrue(!country.isEmpty());

		boolean isPresent = true;

		isPresent = country.stream().anyMatch(c -> c.getName().equalsIgnoreCase("Aruba"));

		assertTrue(!isPresent);

	}

}

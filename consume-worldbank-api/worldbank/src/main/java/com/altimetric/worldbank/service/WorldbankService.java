package com.altimetric.worldbank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.altimetric.worldbank.model.CountryWithCapitalCity;
import com.altimetric.worldbank.model.Header;
import com.altimetric.worldbank.model.Worldbank;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WorldbankService {

	private static final String URL = "http://api.worldbank.org/v2/country?format=json";
	ObjectMapper mapper = new ObjectMapper();

	public List<Worldbank> getAllWorldbankDetails() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Object>> objects = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Object>>() {
				});

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		List<Worldbank> countrylist = mapper.convertValue(objects.getBody().get(1),
				new TypeReference<List<Worldbank>>() {
				});

		Header header = mapper.convertValue(objects.getBody().get(0), new TypeReference<Header>() {
		});

		for (int i = 2; i <= header.getPages(); i++) {
			ResponseEntity<List<Object>> obj = restTemplate.exchange(URL + "&page=" + i, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<Object>>() {
					});
			List<Worldbank> pagedCountryList = mapper.convertValue(obj.getBody().get(1),
					new TypeReference<List<Worldbank>>() {
					});
			countrylist.addAll(pagedCountryList);
		}

		return countrylist;
	}

	public Worldbank getWorldbankCountryByName(String name) {

		Optional<Worldbank> country = this.getAllWorldbankDetails().stream()
				.filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();

		return country.isPresent() ? country.get() : null;
	}

	public List<CountryWithCapitalCity> getWorldBankCountriesWithCapitalCity(String name, boolean regionSelected) {

		List<Worldbank> mainList = this.getAllWorldbankDetails();

		Optional<Worldbank> country = mainList.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();

		String region = "";

		if (country.isPresent()) {
			region = country.get().getRegion().getValue();
		}

		List<Worldbank> resultList = new ArrayList<Worldbank>();

		for (Worldbank c : mainList) {
			if (regionSelected && region.equalsIgnoreCase(c.getRegion().getValue())
					&& !c.getName().equalsIgnoreCase(country.get().getName()))
				resultList.add(c);
		}

		List<CountryWithCapitalCity> countryWithCapitalList = new ArrayList<CountryWithCapitalCity>();

		for (Worldbank r : resultList) {

			CountryWithCapitalCity cwc = new CountryWithCapitalCity();
			cwc.setCapitalCityName(r.getCapitalCity());
			cwc.setCountryName(r.getName());

			countryWithCapitalList.add(cwc);
		}

		return countryWithCapitalList;

	}

	public List<CountryWithCapitalCity> getWorldBankCountriesWithCapitalCity(String name, boolean regionSelected,
			boolean incomeLevelSelected, boolean lendingTypeSelected) {

		List<Worldbank> mainList = this.getAllWorldbankDetails();

		Optional<Worldbank> country = mainList.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();

		String region = "";
		String incomeLevel = "";
		String lendingType = "";

		if (country.isPresent()) {
			region = country.get().getRegion().getValue();
			incomeLevel = country.get().getIncomeLevel().getValue();
			lendingType = country.get().getLendingType().getValue();
		}

		List<Worldbank> resultList = new ArrayList<Worldbank>();

		for (Worldbank c : mainList) {

			if (regionSelected && incomeLevelSelected && lendingTypeSelected
					&& region.equalsIgnoreCase(c.getRegion().getValue())
					&& !c.getName().equalsIgnoreCase(country.get().getName())
					&& incomeLevel.equalsIgnoreCase(c.getIncomeLevel().getValue())
					&& lendingType.equalsIgnoreCase(c.getLendingType().getValue()))
				resultList.add(c);

			else if (regionSelected && incomeLevelSelected && region.equalsIgnoreCase(c.getRegion().getValue())
					&& !c.getName().equalsIgnoreCase(country.get().getName())
					&& incomeLevel.equalsIgnoreCase(c.getIncomeLevel().getValue()))
				resultList.add(c);

			else if (regionSelected && region.equalsIgnoreCase(c.getRegion().getValue())
					&& !c.getName().equalsIgnoreCase(country.get().getName()))
				resultList.add(c);
		}

		List<CountryWithCapitalCity> countryWithCapitalList = new ArrayList<CountryWithCapitalCity>();

		for (Worldbank r : resultList) {

			CountryWithCapitalCity cwc = new CountryWithCapitalCity();
			cwc.setCapitalCityName(r.getCapitalCity());
			cwc.setCountryName(r.getName());

			countryWithCapitalList.add(cwc);
		}

		return countryWithCapitalList;

	}

}

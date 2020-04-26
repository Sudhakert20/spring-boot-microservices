package com.finra.assignment.phonenumberalphanumericcombinations;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.finra.assignment.phonenumberalphanumericcombinations.controller.PhoneNumberAlphaNumericCombinationsController;
import com.finra.assignment.phonenumberalphanumericcombinations.model.Combinations;
import com.finra.assignment.phonenumberalphanumericcombinations.repository.PhoneNumberAlphaNumericCombinationsRepository;
import com.finra.assignment.phonenumberalphanumericcombinations.service.PhoneNumberAlphaNumericCombinationsService;
import com.finra.assignment.phonenumberalphanumericcombinations.service.PhoneNumberAlphaNumericCombinationsServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PhoneNumberAlphaNumericCombinationsController.class)
public class PhoneAlphaCombinationControllerTest {

	@Autowired
	MockMvc mvc;

	@TestConfiguration
	static class Configuration {
		@Bean
		public PhoneNumberAlphaNumericCombinationsService phoneNumberAlphaNumericCombinationsService() {
			return new PhoneNumberAlphaNumericCombinationsServiceImpl();
		}
	}

	@Autowired
	PhoneNumberAlphaNumericCombinationsService service;

	@MockBean
	PhoneNumberAlphaNumericCombinationsRepository repository;

	@Test
	public void testFinra() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/finra"))
				.andExpect(MockMvcResultMatchers.content().string("Hello FINRA"));
	}

	@Test
	public void testPhoneCombinationInvalidLength() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/finra/11223344")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Pleae Enter a valid 7 or 10 digit phone number."))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPhoneCombinationsInvalidFormat() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/finra/qwertyu")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.string("qwertyu is not valid entry. Please enter a valid 7 or 10 digit phone number."))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testPhoneCombinationValid() throws Exception {

		Combinations com1 = new Combinations(1111112L, "111111A");
		Combinations com2 = new Combinations(1111112L, "111111B");
		Combinations com3 = new Combinations(1111112L, "111111C");

		List<Combinations> combs = new ArrayList<>();
		combs.add(com1);
		combs.add(com2);
		combs.add(com3);

		Mockito.when(repository.findByPhoneNumber(1111112L)).thenReturn(combs);

		int pageNumber = 1;
		int pageSize = 1;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Combinations> pagedTasks = new PageImpl<>(combs);
		Mockito.when(this.repository.findByPhoneNumber(1111112L, pageable)).thenReturn(pagedTasks);

		mvc.perform(MockMvcRequestBuilders.get("/finra/1111112")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$['pageable']['paged']").value("true"))
				.andExpect(MockMvcResultMatchers.jsonPath("$['content'][0].['combination']").value("111111A"))
				.andDo(MockMvcResultHandlers.print());
	}

}

package com.altimetric.worldbank;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.altimetric.worldbank.service.WorldbankService;

@Profile("test")
@Configuration
public class WorldbankServiceTestConfiguration {
	
	   @Bean
	   @Primary
	   public WorldbankService worldbankService() {
	      return Mockito.mock(WorldbankService.class);
	   }

}

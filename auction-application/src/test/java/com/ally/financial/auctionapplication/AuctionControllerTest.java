package com.ally.financial.auctionapplication;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ally.financial.auctionapplication.controller.AuctionController;
import com.ally.financial.auctionapplication.model.Auction;
import com.ally.financial.auctionapplication.model.Item;
import com.ally.financial.auctionapplication.repository.AuctionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuctionController.class)
public class AuctionControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	@MockBean
	AuctionRepository repo;

	@Test
	public void testController() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/auction"))
				.andExpect(MockMvcResultMatchers.content().string("Welcome to Ally Auction."));
	}

	@Test
	public void testGetAuction() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/auction/auctionItems"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetAuctionById() throws Exception {
		Auction auction = new Auction();
		auction.setReservePrice(400.00);
		auction.setItem(new Item("phone", "phone Description"));
		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(auction));
		mvc.perform(MockMvcRequestBuilders.get("/auction/auctionItems/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPostAuctionItem() throws JsonProcessingException, Exception {
		
		Auction auction = new Auction();
		auction.setReservePrice(400.00);
		auction.setItem(new Item("phone", "phone Description"));
		auction.setAuctionItemId(1L);
		
		Mockito.when(repo.save(auction)).thenReturn(auction);
		
		mvc.perform(MockMvcRequestBuilders.post("/auction/auctionItems")
                .content(new ObjectMapper().writeValueAsString(auction))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
 
	}

}

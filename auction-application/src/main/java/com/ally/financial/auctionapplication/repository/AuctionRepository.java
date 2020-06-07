package com.ally.financial.auctionapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ally.financial.auctionapplication.model.Auction;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

}

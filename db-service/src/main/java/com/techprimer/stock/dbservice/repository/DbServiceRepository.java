package com.techprimer.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techprimer.stock.dbservice.model.Stock;

@Repository
public interface DbServiceRepository extends JpaRepository<Stock, Integer>{

	List<Stock> findByUser(String user);

}

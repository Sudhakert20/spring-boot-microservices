package com.finra.assignment.phonenumberalphanumericcombinations.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finra.assignment.phonenumberalphanumericcombinations.model.Combinations;

@Repository
public interface PhoneNumberAlphaNumericCombinationsRepository extends JpaRepository<Combinations, Long> {

	List<Combinations> findByPhoneNumber(Long phoneNumber);
	
	Page<Combinations> findByPhoneNumber(Long number, Pageable pageable);
	
}

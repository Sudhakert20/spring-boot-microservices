package com.finra.assignment.phonenumberalphanumericcombinations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finra.assignment.phonenumberalphanumericcombinations.model.Combination;

@Repository
public interface PhoneNumberAlphaNumericCombinationsRepository extends JpaRepository<Combination, Long> {

	
}

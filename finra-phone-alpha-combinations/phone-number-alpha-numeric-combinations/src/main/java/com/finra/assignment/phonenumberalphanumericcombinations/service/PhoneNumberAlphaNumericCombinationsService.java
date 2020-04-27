package com.finra.assignment.phonenumberalphanumericcombinations.service;

import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.finra.assignment.phonenumberalphanumericcombinations.model.Combinations;

public interface PhoneNumberAlphaNumericCombinationsService {

	Page<Combinations> getCombinations(@Size(min=7, max=10) String number, Pageable pageable);

}

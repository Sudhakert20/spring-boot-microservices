package com.finra.assignment.phonenumberalphanumericcombinations.service;

import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.finra.assignment.phonenumberalphanumericcombinations.model.Combination;

public interface PhoneNumberAlphaNumericCombinationsService {

	Page<Combination> getCombinations(@Size(min=7, max=10) String number, Pageable pageable);

}

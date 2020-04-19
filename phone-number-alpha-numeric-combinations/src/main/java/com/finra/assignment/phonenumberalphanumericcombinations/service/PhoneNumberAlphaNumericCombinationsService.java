package com.finra.assignment.phonenumberalphanumericcombinations.service;

import java.util.List;

import javax.validation.constraints.Size;

public interface PhoneNumberAlphaNumericCombinationsService {

	List<String> getCombinations(@Size(min=7, max=10) String number);

}

package com.finra.assignment.phonenumberalphanumericcombinations.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberAlphaNumericCombinationsServiceImpl implements PhoneNumberAlphaNumericCombinationsService {

	@Override
	public List<String> getCombinations(@Size(min = 7, max = 10) String number) {

		int size = number.length();

		List<String> combinations = new ArrayList<>();

		List<String> temp = new ArrayList<>();

		String[] keypad = { "", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };

		StringBuilder str = new StringBuilder(number);
		for (int i = size - 1; i >= 0; i--) {
			int index = Character.getNumericValue(number.charAt(i));
			String key = keypad[index];
			if (i == size - 1) {
				for (char ch : key.toCharArray()) {
					str.setCharAt(i, ch);
					combinations.add(str.toString());
				}
			} else {
				for (String t : temp) {
					StringBuilder s = new StringBuilder(t);
					for (char ch : key.toCharArray()) {
						s.setCharAt(i, ch);
						if (!combinations.contains(s.toString()))
							combinations.add(s.toString());
					}
				}
			}
			temp.clear();
			temp.addAll(combinations);
		}

		return combinations;
	}

}

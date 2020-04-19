package com.finra.assignment.phonenumberalphanumericcombinations.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;

@Service
public class PhoneNumberAlphaNumericCombinationsServiceImpl implements PhoneNumberAlphaNumericCombinationsService {

	@Override
	public List<String> getCombinations(@Size(min = 7, max = 10) String number) {

		// Gives all the possible alpha numeric combinations of given number.
		int size = number.length();
		
		List<String> combinations = new ArrayList<>();
		
		List<String> temp = new ArrayList<>();

		String[] keypad = { "", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };

		StringBuilder str = new StringBuilder(number);
		for (int i = size - 1; i >= 0; i--) {
			int index = Character.getNumericValue(number.charAt(i));
			String key = keypad[index];
			if (i == size - 1 || temp.isEmpty()) {
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

	// brute force approach to find all the mnemonics(alpha only)(not alpha numeric)
	// of given phone number.
	//call or change access of getMnemonics(number, keypad) to get Mnemonic values;
	@SuppressWarnings("unused")
	private void getMnemonics(String number, String[] keypad) {
		char[] partials = new char[number.length()];
		List<String> mnemonics = new ArrayList<>();
		phoneMnemonicsHelper(number, keypad, 0, partials, mnemonics);
		System.out.println("Size: " + mnemonics.size() + " => Mnemonics to return: " + mnemonics);
	}

	private void phoneMnemonicsHelper(@Size(min = 7, max = 10) String number, String[] keypad, int digit,
			char[] partials, List<String> mnemonics) {

		if (digit == number.length()) {
			mnemonics.add(new String(partials));
		} else {
			for (int i = 0; i < keypad[number.charAt(digit) - '0'].length(); i++) {
				char c = keypad[number.charAt(digit) - '0'].charAt(i);
				partials[digit] = c;
				phoneMnemonicsHelper(number, keypad, digit + 1, partials, mnemonics);
			}
		}

	}

}

package com.finra.assignment.phonenumberalphanumericcombinations.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finra.assignment.phonenumberalphanumericcombinations.model.Combinations;
import com.finra.assignment.phonenumberalphanumericcombinations.repository.PhoneNumberAlphaNumericCombinationsRepository;

@Service
public class PhoneNumberAlphaNumericCombinationsServiceImpl implements PhoneNumberAlphaNumericCombinationsService {

	public static final String[] keypad = { "0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };

	@Autowired
	PhoneNumberAlphaNumericCombinationsRepository repository;

	@Override
	public Page<Combinations> getCombinations(@Size(min = 7, max = 10) String number, Pageable pageable) {

		long num = Long.parseLong(number);
		List<Combinations> list = repository.findByPhoneNumber(num);
		if (list != null && !list.isEmpty()) {
			return repository.findByPhoneNumber(num, pageable);
		}

		List<String> mnemonics = new ArrayList<>();

		getMnemonics(number, mnemonics);
		// getAlphaNumericMnemonics(number, mnemonics);

		mnemonics.forEach(a -> repository.save(new Combinations(num, a)));

		return repository.findByPhoneNumber(num, pageable);
	}

	// brute force approach to find all the mnemonics of given phone number.
	public List<String> getMnemonics(String number, List<String> mnemonics) {
		char[] partials = number.toCharArray();
		phoneMnemonicsHelper(number, 0, partials, mnemonics);
		return mnemonics;
	}

	private void phoneMnemonicsHelper(String number, int digit, char[] partials, List<String> mnemonics) {

		if (digit == number.length()) {
			mnemonics.add(new String(partials));
		} else {
			for (int i = 0; i < keypad[number.charAt(digit) - '0'].length(); i++) {
				char c = keypad[number.charAt(digit) - '0'].charAt(i);
				partials[digit] = c;
				phoneMnemonicsHelper(number, digit + 1, partials, mnemonics);
			}
		}

	}

	public List<String> getAlphaNumericMnemonics(String number, List<String> mnemonics) {
		List<String> temp = new ArrayList<>();
		StringBuilder str = new StringBuilder(number);

		for (int i = number.length() - 1; i >= 0; i--) {
			int index = Character.getNumericValue(number.charAt(i));
			String key = keypad[index];
			if (i == number.length() - 1) {
				for (char ch : key.toCharArray()) {
					str.setCharAt(i, ch);
					mnemonics.add(str.toString());
				}
			} else {
				for (String t : temp) {
					StringBuilder s = new StringBuilder(t);
					for (char ch : key.toCharArray()) {
						s.setCharAt(i, ch);
						if (!mnemonics.contains(s.toString()))
							mnemonics.add(s.toString());
					}
				}
			}
			temp.clear();
			temp.addAll(mnemonics);
		}
		return mnemonics;
	}

}

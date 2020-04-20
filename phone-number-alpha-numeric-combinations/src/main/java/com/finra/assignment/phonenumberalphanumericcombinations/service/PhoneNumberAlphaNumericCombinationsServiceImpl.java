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

	@Autowired
	PhoneNumberAlphaNumericCombinationsRepository repository;

	@Override
	public Page<Combinations> getCombinations(@Size(min = 7, max = 10) String number, Pageable pageable) {

		List<Combinations> list = repository.findByPhoneNumber(Long.parseLong(number));
		if (list != null && !list.isEmpty()) {
			return repository.findByPhoneNumber(Long.parseLong(number), pageable);
		}
		
		int size = number.length();
		List<String> mnemonics = new ArrayList<>();
		List<String> temp = new ArrayList<>();

		String[] keypad = { "", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };

		StringBuilder str = new StringBuilder(number);
		for (int i = size - 1; i >= 0; i--) {
			int index = Character.getNumericValue(number.charAt(i));
			String key = keypad[index];
			if (i == size - 1 || temp.isEmpty()) {
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
		mnemonics.forEach(a -> repository.save(new Combinations(Long.parseLong(number), a)));

		return repository.findByPhoneNumber(Long.parseLong(number), pageable);
	}

}

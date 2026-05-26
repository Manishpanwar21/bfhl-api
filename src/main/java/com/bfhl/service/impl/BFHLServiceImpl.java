package com.bfhl.service.impl;

import com.bfhl.dto.BFHLRequestDTO;
import com.bfhl.dto.BFHLResponseDTO;
import com.bfhl.service.BFHLService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BFHLServiceImpl implements BFHLService {

    private static final String USER_ID = "manish_panwar_08272003";
    private static final String EMAIL = "manish.panwar@example.com";
    private static final String ROLL_NUMBER = "0827CI231076";

    @Override
    public BFHLResponseDTO process(BFHLRequestDTO request) {
        BFHLResponseDTO response = new BFHLResponseDTO();
        response.setSuccess(true);
        response.setUserId(USER_ID);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        StringBuilder alphabetConcat = new StringBuilder();
        long numericSum = 0;

        List<String> data = request.getData();
        if (data != null) {
            for (String item : data) {
                if (item == null || item.isEmpty()) {
                    continue;
                }

                if (isNumeric(item)) {
                    if (isOdd(item)) {
                        oddNumbers.add(item);
                    } else {
                        evenNumbers.add(item);
                    }
                    numericSum += parseNumericValue(item);
                } else if (isAlphabetic(item)) {
                    String upper = item.toUpperCase();
                    alphabets.add(upper);
                    alphabetConcat.append(upper);
                } else {
                    specialCharacters.add(item);
                }
            }
        }

        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(numericSum));
        response.setConcatString(buildAlternatingCapsReverse(alphabetConcat.toString()));

        return response;
    }

    private boolean isNumeric(String value) {
        if (value.charAt(0) == '-' && value.length() > 1) {
            for (int i = 1; i < value.length(); i++) {
                if (!Character.isDigit(value.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return !value.isEmpty();
    }

    private boolean isAlphabetic(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isLetter(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isOdd(String value) {
        return parseNumericValue(value) % 2 != 0;
    }

    private long parseNumericValue(String value) {
        return Long.parseLong(value);
    }

    private String buildAlternatingCapsReverse(String alphabetString) {
        if (alphabetString.isEmpty()) {
            return "";
        }

        String reversed = new StringBuilder(alphabetString).reverse().toString();
        char[] chars = reversed.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                chars[i] = Character.toLowerCase(chars[i]);
            } else {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        return new String(chars);
    }
}

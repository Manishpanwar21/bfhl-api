package com.bfhl.service.impl;

import com.bfhl.dto.BFHLRequestDTO;
import com.bfhl.dto.BFHLResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BFHLServiceImplTest {

    private BFHLServiceImpl bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BFHLServiceImpl();
    }

    @Test
    void process_shouldClassifyOddAndEvenNumbers() {
        BFHLRequestDTO request = new BFHLRequestDTO(Arrays.asList("1", "2", "3", "4"));

        BFHLResponseDTO response = bfhlService.process(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("1", "3"), response.getOddNumbers());
        assertEquals(List.of("2", "4"), response.getEvenNumbers());
        assertEquals("10", response.getSum());
    }

    @Test
    void process_shouldUppercaseAlphabets() {
        BFHLRequestDTO request = new BFHLRequestDTO(List.of("a", "Bc", "xyz"));

        BFHLResponseDTO response = bfhlService.process(request);

        assertEquals(List.of("A", "BC", "XYZ"), response.getAlphabets());
        assertEquals("zYxCbA", response.getConcatString());
    }

    @Test
    void process_shouldCollectSpecialCharacters() {
        BFHLRequestDTO request = new BFHLRequestDTO(Arrays.asList("@", "a#b", "5"));

        BFHLResponseDTO response = bfhlService.process(request);

        assertEquals(List.of("@", "a#b"), response.getSpecialCharacters());
        assertEquals(List.of("5"), response.getOddNumbers());
    }

    @Test
    void process_shouldReturnFixedUserDetails() {
        BFHLRequestDTO request = new BFHLRequestDTO(Collections.emptyList());

        BFHLResponseDTO response = bfhlService.process(request);

        assertEquals("manish_panwar_08272003", response.getUserId());
        assertEquals("manish.panwar@example.com", response.getEmail());
        assertEquals("0827CI231076", response.getRollNumber());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void process_shouldHandleMixedInput() {
        BFHLRequestDTO request = new BFHLRequestDTO(
                Arrays.asList("2", "A", "!", "10", "b", "3"));

        BFHLResponseDTO response = bfhlService.process(request);

        assertEquals(List.of("3"), response.getOddNumbers());
        assertEquals(List.of("2", "10"), response.getEvenNumbers());
        assertEquals(List.of("A", "B"), response.getAlphabets());
        assertEquals(List.of("!"), response.getSpecialCharacters());
        assertEquals("15", response.getSum());
        assertEquals("bA", response.getConcatString());
    }

    @Test
    void process_shouldHandleNullDataAsEmpty() {
        BFHLRequestDTO request = new BFHLRequestDTO();
        request.setData(null);

        BFHLResponseDTO response = bfhlService.process(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
    }

    @Test
    void process_shouldKeepNumbersAsStrings() {
        BFHLRequestDTO request = new BFHLRequestDTO(List.of("001", "002"));

        BFHLResponseDTO response = bfhlService.process(request);

        assertEquals(List.of("001"), response.getOddNumbers());
        assertEquals(List.of("002"), response.getEvenNumbers());
    }
}

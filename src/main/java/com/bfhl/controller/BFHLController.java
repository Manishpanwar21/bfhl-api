package com.bfhl.controller;

import com.bfhl.dto.BFHLRequestDTO;
import com.bfhl.dto.BFHLResponseDTO;
import com.bfhl.service.BFHLService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BFHLController {

    private final BFHLService bfhlService;

    public BFHLController(BFHLService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<BFHLResponseDTO> processBfhl(@RequestBody BFHLRequestDTO request) {
        BFHLResponseDTO response = bfhlService.process(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BFHLResponseDTO> handleException(Exception ex) {
        BFHLResponseDTO errorResponse = new BFHLResponseDTO();
        errorResponse.setSuccess(false);
        errorResponse.setUserId("manish_panwar_08272003");
        errorResponse.setEmail("manish.panwar@example.com");
        errorResponse.setRollNumber("0827CI231076");
        errorResponse.setSum("0");
        errorResponse.setConcatString("");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

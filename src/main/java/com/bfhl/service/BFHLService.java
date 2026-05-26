package com.bfhl.service;

import com.bfhl.dto.BFHLRequestDTO;
import com.bfhl.dto.BFHLResponseDTO;

public interface BFHLService {

    BFHLResponseDTO process(BFHLRequestDTO request);
}

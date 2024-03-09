package org.flab.hyunsb.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.exception.RegionInvalidException;
import org.flab.hyunsb.application.output.RegionOutputPort;
import org.flab.hyunsb.application.usecase.region.ValidateRegionUseCase;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegionService implements ValidateRegionUseCase {

    private final RegionOutputPort regionOutputPort;

    @Override
    public void validateRegionId(Long id) {
        regionOutputPort.findById(id).orElseThrow(RegionInvalidException::new);
    }
}


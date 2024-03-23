package org.flab.hyunsb.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.exception.constraint.RegionInvalidException;
import org.flab.hyunsb.application.output.RegionOutputPort;
import org.flab.hyunsb.application.validator.RegionValidator;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegionService implements RegionValidator {

    private final RegionOutputPort regionOutputPort;

    @Override
    public void validateRegionId(Long id) {
        regionOutputPort.findById(id).orElseThrow(RegionInvalidException::new);
    }
}


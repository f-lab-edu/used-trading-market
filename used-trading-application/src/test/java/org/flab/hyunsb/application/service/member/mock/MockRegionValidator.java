package org.flab.hyunsb.application.service.member.mock;

import org.flab.hyunsb.application.exception.constraint.RegionInvalidException;
import org.flab.hyunsb.application.validator.RegionValidator;

public class MockRegionValidator implements RegionValidator {

    private final Long InvalidRegionId;

    public MockRegionValidator(Long invalidRegionId) {
        InvalidRegionId = invalidRegionId;
    }

    @Override
    public void validateRegionId(Long id) {
        if (InvalidRegionId.equals(id)) {
            throw new RegionInvalidException();
        }
    }
}

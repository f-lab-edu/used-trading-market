package org.flab.hyunsb.application.service.member.mock;

import org.flab.hyunsb.application.exception.RegionInvalidException;
import org.flab.hyunsb.application.usecase.region.ValidateRegionUseCase;

public class MockValidateRegionUseCase implements ValidateRegionUseCase {

    private final Long InvalidRegionId;

    public MockValidateRegionUseCase(Long invalidRegionId) {
        InvalidRegionId = invalidRegionId;
    }

    @Override
    public void validateRegionId(Long id) {
        if (InvalidRegionId.equals(id)) {
            throw new RegionInvalidException();
        }
    }
}

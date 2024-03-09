package org.flab.hyunsb.application.service.region.mock;

import java.util.Optional;
import org.flab.hyunsb.application.output.RegionOutputPort;
import org.flab.hyunsb.domain.region.Region;

public class MockRegionOutputPort implements RegionOutputPort {

    private static final Region MOCK_REGION = new Region(
        1L, "경기도", "시흥시", 0.0, 0.0
    );

    private final Long invalidRegionId;

    public MockRegionOutputPort(Long invalidRegionId) {
        this.invalidRegionId = invalidRegionId;
    }

    @Override
    public Optional<Region> findById(Long id) {
        if (invalidRegionId.equals(id)) {
            return Optional.empty();
        }
        return Optional.of(MOCK_REGION);
    }
}

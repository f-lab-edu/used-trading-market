package org.flab.hyunsb.application.output;

import java.util.Optional;
import org.flab.hyunsb.domain.region.Region;

public interface RegionOutputPort {

    Optional<Region> findById(Long id);
}

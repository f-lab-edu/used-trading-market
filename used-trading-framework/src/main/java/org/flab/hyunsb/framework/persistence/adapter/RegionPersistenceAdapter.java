package org.flab.hyunsb.framework.persistence.adapter;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.output.RegionOutputPort;
import org.flab.hyunsb.domain.region.Region;
import org.flab.hyunsb.framework.persistence.entity.region.RegionEntity;
import org.flab.hyunsb.framework.persistence.repository.RegionRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionPersistenceAdapter implements RegionOutputPort {

    private final RegionRepository regionRepository;

    @Override
    public Optional<Region> findById(Long id) {
        return regionRepository.findById(id)
            .map(RegionEntity::toDomain)
            .or(Optional::empty);
    }
}

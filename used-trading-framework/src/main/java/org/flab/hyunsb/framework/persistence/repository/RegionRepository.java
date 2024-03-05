package org.flab.hyunsb.framework.persistence.repository;

import org.flab.hyunsb.framework.persistence.entity.region.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

}

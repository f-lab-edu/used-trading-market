package org.flab.hyunsb.framework.persistence.adapter;

import java.util.Optional;
import org.flab.hyunsb.domain.region.Region;
import org.flab.hyunsb.framework.persistence.entity.region.RegionEntity;
import org.flab.hyunsb.framework.persistence.repository.RegionRepository;
import org.flab.hyunsb.framework.repository.annotation.RepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryTest
class RegionPersistenceAdapterTest {

    @Autowired
    private RegionPersistenceAdapter regionPersistenceAdapter;

    private RegionEntity testRegionEntity;

    @BeforeEach
    void setUp(@Autowired RegionRepository regionRepository) {
        RegionEntity region = new RegionEntity(1L, "경기도", "시흥시", 0.0, 0.0);
        testRegionEntity = regionRepository.save(region);
    }

    @Test
    @DisplayName("[지역 ID 조회 성공 테스트] 매핑되는 Id가 존재할 시, 도메인 객체를 반환한다.")
    public void findRegionById_successTest() {
        // Given
        Long regionId = testRegionEntity.getId();
        // When
        Optional<Region> optionalRegion = regionPersistenceAdapter.findById(regionId);
        // Then
        Assertions.assertAll(
            () -> Assertions.assertTrue(optionalRegion.isPresent()),
            () -> Assertions.assertEquals(regionId, optionalRegion.get().regionId())
        );
    }

    @Test
    @DisplayName("[지역 ID 조회 성공 테스트] 매핑되는 Id가 존재하지 않을 시, 빈 Optional 객체를 반환한다.")
    public void findRegionById_successTest_regionNotExist() {
        // Given
        Long invalidRegionId = 0L;
        // When
        Optional<Region> optionalRegion = regionPersistenceAdapter.findById(invalidRegionId);
        // Then
        Assertions.assertAll(
            () -> Assertions.assertTrue(optionalRegion.isEmpty())
        );
    }
}
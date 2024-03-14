package org.flab.hyunsb.domain.region;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegionTest {

    @Test
    @DisplayName("[지역 생성 성공 테스트] 지역 객체를 생성한다.")
    public void regionConstruct_successTest() {
        // Given
        Long regionId = 1L;
        String sido = "경기도";
        String sigungu = "시흥시";
        Double lat = 0.0;
        Double lng = 0.0;
        // When
        Region actual = new Region(regionId, sido, sigungu, lat, lng);
        // Then
        assertAll(
            () -> Assertions.assertEquals(actual.regionId(), regionId),
            () -> Assertions.assertEquals(actual.sido(), sido),
            () -> Assertions.assertEquals(actual.sigungu(), sigungu),
            () -> Assertions.assertEquals(actual.lat(), lat),
            () -> Assertions.assertEquals(actual.lng(), lng)
        );
    }
}
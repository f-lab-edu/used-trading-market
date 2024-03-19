package org.flab.hyunsb.application.service.region;

import static org.assertj.core.api.Assertions.*;

import org.flab.hyunsb.application.exception.RegionInvalidException;
import org.flab.hyunsb.application.exception.message.RegionErrorMessage;
import org.flab.hyunsb.application.output.RegionOutputPort;
import org.flab.hyunsb.application.service.RegionService;
import org.flab.hyunsb.application.service.region.mock.MockRegionOutputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RegionServiceTest {

    private static final Long VALID_REGION_ID = 1L;
    private static final Long INVALID_REGION_ID = 0L;

    private RegionService regionService;

    @BeforeEach
    void setUp() {
        RegionOutputPort regionOutputPort = new MockRegionOutputPort(INVALID_REGION_ID);
        regionService = new RegionService(regionOutputPort);
    }

    @Nested
    @DisplayName("지역 식별자 검증 서비스 단위 테스트")
    class RegionServiceValidate {

        @Test
        @DisplayName("[지역 검증 성공 테스트] 유요한 지역 번호를 요청한 경우 아무런 동작을 수행하지 않는다.")
        public void validateRegionId_successTest() {
            // Given & When & Then
            Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() ->
                    regionService.validateRegionId(VALID_REGION_ID))
            );
        }

        @Test
        @DisplayName("[지역 검증 실패 테스트] 유요하지 않은 지역 번호를 요청한 경우 예외를 발생한다.")
        public void validateRegionId_failureTest_causeInvalidRegionId() {
            // Given & When & Then
            Assertions.assertAll(
                () -> assertThatThrownBy(() -> regionService.validateRegionId(INVALID_REGION_ID))
                    .isInstanceOf(RegionInvalidException.class)
                    .hasMessage(RegionErrorMessage.INVALID_REGION_ID.getMessage())
            );
        }
    }
}

package org.flab.hyunsb.application.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegionErrorMessage {

    INVALID_REGION_ID("유효하지 않은 지역 정보입니다.");

    private final String message;
}

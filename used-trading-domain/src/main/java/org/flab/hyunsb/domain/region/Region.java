package org.flab.hyunsb.domain.region;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Region {

    private final Long regionId;
    private final String sido;
    private final String sigungu;
    private final Double lat;
    private final Double lng;
}

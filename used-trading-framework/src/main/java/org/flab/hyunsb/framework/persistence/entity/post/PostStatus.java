package org.flab.hyunsb.framework.persistence.entity.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostStatus {

    RESERVED("예약 중"),
    SOLD("판매 완료"),
    SELLING("판매 중"),
    HIDE("숨김");

    private final String status;
}

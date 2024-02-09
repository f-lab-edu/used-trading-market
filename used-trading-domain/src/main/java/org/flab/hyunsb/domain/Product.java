package org.flab.hyunsb.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {

    private final Long id;
    private final String name;
    private final String description;
}

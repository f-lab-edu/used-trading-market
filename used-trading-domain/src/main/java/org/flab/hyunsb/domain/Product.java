package org.flab.hyunsb.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private final Long id;
    private final String name;
    private final String description;

    public Product(String name, String description) {
        this.id = null;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        Objects.requireNonNull(id);
        return id;
    }
}

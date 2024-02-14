package org.flab.hyunsb.bootstrap.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.hyunsb.domain.Product;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    @NotEmpty(message = "Name may not be empty")
    private String name;

    @NotEmpty(message = "Description may not be empty")
    private String description;

    public Product toEntity() {
        return new Product(name, description);
    }
}


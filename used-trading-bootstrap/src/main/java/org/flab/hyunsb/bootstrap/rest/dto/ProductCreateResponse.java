package org.flab.hyunsb.bootstrap.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.hyunsb.domain.Product;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateResponse {

    private Long id;
    private String name;
    private String description;

    public static ProductCreateResponse from(Product product) {
        return ProductCreateResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .build();
    }
}

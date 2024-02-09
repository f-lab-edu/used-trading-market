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
public class ProductQueryResponse {

    private Long id;
    private String name;
    private String description;

    public static ProductQueryResponse from(Product product) {
        return ProductQueryResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .build();
    }
}

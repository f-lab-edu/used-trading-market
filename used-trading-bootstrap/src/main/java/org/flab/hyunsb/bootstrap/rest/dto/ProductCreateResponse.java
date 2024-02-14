package org.flab.hyunsb.bootstrap.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.hyunsb.domain.Product;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateResponse {

    private Long id;
    private String name;
    private String description;

    public static ProductCreateResponse from(Product product) {
        return new ProductCreateResponse(
            product.getId(),
            product.getName(),
            product.getDescription()
        );
    }
}

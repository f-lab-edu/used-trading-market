package org.flab.hyunsb.bootstrap.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.hyunsb.domain.Product;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFindResponse {

    private Long id;
    private String name;
    private String description;

    public static ProductFindResponse from(Product product) {
        return new ProductFindResponse(
            product.getId(),
            product.getName(),
            product.getDescription()
        );
    }
}

package org.flab.hyunsb.framework.persistence.mapper;

import org.flab.hyunsb.domain.Product;
import org.flab.hyunsb.framework.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapper {

    public Product toDomain(ProductEntity productEntity) {
        return Product.builder()
            .id(productEntity.getId())
            .name(productEntity.getName())
            .description(productEntity.getDescription())
            .build();
    }

    public ProductEntity from(Product product) {
        return ProductEntity.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .build();
    }
}

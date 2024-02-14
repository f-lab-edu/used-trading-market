package org.flab.hyunsb.framework.persistence.mapper;

import org.flab.hyunsb.domain.Product;
import org.flab.hyunsb.framework.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapper {

    public Product toDomain(ProductEntity productEntity) {
        return new Product(
            productEntity.getId(),
            productEntity.getName(),
            productEntity.getDescription()
        );
    }

    public ProductEntity from(Product product) {
        return new ProductEntity(
            product.getName(),
            product.getDescription()
        );
    }
}

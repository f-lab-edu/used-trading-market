package org.flab.hyunsb.framework.persistence.adapter;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.output.ProductOutputPort;
import org.flab.hyunsb.domain.Product;
import org.flab.hyunsb.framework.persistence.entity.ProductEntity;
import org.flab.hyunsb.framework.persistence.mapper.ProductEntityMapper;
import org.flab.hyunsb.framework.persistence.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductOutputPort {

    private final ProductRepository productRepository;
    private final ProductEntityMapper mapper;

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = mapper.from(product);
        productEntity = productRepository.save(productEntity);
        return mapper.toDomain(productEntity);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if (optionalProductEntity.isEmpty()) {
            return Optional.empty();
        }
        ProductEntity productEntity = optionalProductEntity.get();
        return Optional.of(mapper.toDomain(productEntity));
    }
}

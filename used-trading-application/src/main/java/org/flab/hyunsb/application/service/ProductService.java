package org.flab.hyunsb.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.exceptions.ProductNotFoundException;
import org.flab.hyunsb.application.output.ProductOutputPort;
import org.flab.hyunsb.application.usecase.CreateProductUseCase;
import org.flab.hyunsb.application.usecase.GetProductUseCase;
import org.flab.hyunsb.domain.Product;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase {

    private final ProductOutputPort productOutputPort;

    @Override
    public Product createProduct(Product product) {
        return productOutputPort.saveProduct(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productOutputPort.getProductById(id).orElseThrow(() ->
            new ProductNotFoundException("Product not found with id=" + id)
        );
    }
}

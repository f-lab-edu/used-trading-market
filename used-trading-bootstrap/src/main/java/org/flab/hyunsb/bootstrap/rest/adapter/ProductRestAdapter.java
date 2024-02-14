package org.flab.hyunsb.bootstrap.rest.adapter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.usecase.CreateProductUseCase;
import org.flab.hyunsb.application.usecase.GetProductUseCase;
import org.flab.hyunsb.bootstrap.rest.dto.ProductCreateRequest;
import org.flab.hyunsb.bootstrap.rest.dto.ProductCreateResponse;
import org.flab.hyunsb.bootstrap.rest.dto.ProductFindResponse;
import org.flab.hyunsb.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductRestAdapter {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreateResponse createProduct(
        @RequestBody @Valid ProductCreateRequest productCreateRequest) {

        Product product = productCreateRequest.toEntity();
        product = createProductUseCase.createProduct(product);

        return  ProductCreateResponse.from(product);
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductFindResponse getProduct(@PathVariable long id) {
        Product product = getProductUseCase.getProductById(id);

        return ProductFindResponse.from(product);
    }
}

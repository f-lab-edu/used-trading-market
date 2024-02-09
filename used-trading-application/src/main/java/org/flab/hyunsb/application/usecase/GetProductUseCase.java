package org.flab.hyunsb.application.usecase;

import org.flab.hyunsb.domain.Product;

public interface GetProductUseCase {

    Product getProductById(Long id);
}

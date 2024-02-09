package org.flab.hyunsb.application.output;

import java.util.Optional;
import org.flab.hyunsb.domain.Product;

public interface ProductOutputPort {

    Product saveProduct(Product product);

    Optional<Product> getProductById(Long id);
}

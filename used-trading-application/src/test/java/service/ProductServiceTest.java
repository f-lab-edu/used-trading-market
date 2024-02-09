package service;

import org.flab.hyunsb.application.output.ProductOutputPort;
import org.flab.hyunsb.application.service.ProductService;
import org.flab.hyunsb.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductOutputPort productOutputPort;

    @Test
    @DisplayName("상품 생성 테스트")
    public void ProductCreateTest() {
        // Given
        Product product = Product.builder()
            .id(1L)
            .name("test")
            .description("testDescription")
            .build();

        Mockito.when(productOutputPort.saveProduct(ArgumentMatchers.any(Product.class)))
            .then(invocation -> invocation.getArgument(0));

        // When
        Product actual = productService.createProduct(product);

        // Then
        Assertions.assertAll(
            () -> Assertions.assertEquals(product, actual),
            () -> Assertions.assertEquals(product.getName(), actual.getName())
        );
    }
}

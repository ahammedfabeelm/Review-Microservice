package com.microservices.review.product;

import com.microservices.review.domain.Product;
import com.microservices.review.repository.ProductRepository;
import com.microservices.review.service.service.impl.ReviewServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URISyntaxException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ReviewServiceImpl reviewService;

    private Product product;

    @BeforeEach
    public void setProduct() {
        product = new Product();
        product.setAverageReviewScore(2);
        product.setProductId("M12345");
        product.setNumberOfReview(12);
    }

    @Test
    void shouldSaveReview() throws URISyntaxException {
        given(productRepository.save(product)).willAnswer(invocation -> invocation.getArgument(0));

        Product created = reviewService.createProduct(product);
        Assertions.assertThat(created).isNotNull();
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void shouldUpdateReview() {
        given(productRepository.save(any(Product.class))).willReturn(product);
        given(productRepository.findByProductId(any(String.class))).willReturn(Optional.of(product));

        final Product expected = reviewService.updateProduct(product);
        Assertions.assertThat(expected).isNotNull();
    }

    @Test
    void shouldDeleteReview() {

        reviewService.deleteProduct(1l);

        verify(productRepository, times(1)).deleteById(1l);
    }

    @Test
    void shouldFindReviewByProduct() {
        given(productRepository.findByProductId("M12")).willReturn(Optional.of(product));
        final Optional<Product> expected = Optional.ofNullable(reviewService.getReview("M12"));
        Assertions.assertThat(expected).isNotNull();
    }
}

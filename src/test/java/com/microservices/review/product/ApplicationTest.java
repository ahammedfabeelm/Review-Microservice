package com.microservices.review.product;

import com.microservices.review.ProductReviewApp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        classes = ProductReviewApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {
    @Test
    void contextLoads() {
    }
}

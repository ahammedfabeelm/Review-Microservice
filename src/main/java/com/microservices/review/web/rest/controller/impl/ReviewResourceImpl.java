package com.microservices.review.web.rest.controller.impl;

import com.microservices.review.domain.Product;
import com.microservices.review.service.service.ReviewService;
import com.microservices.review.web.rest.controller.ReviewResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * REST controller Implementation for managing {@link Product}.
 */
@RestController
public class ReviewResourceImpl implements ReviewResource {

    private final ReviewService reviewService;

    public ReviewResourceImpl(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public ResponseEntity<Product> getReview(String id) {
        return ResponseEntity.ok(reviewService.getReview(id));
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product) throws URISyntaxException {
        Product result = reviewService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product) throws URISyntaxException {
        return ResponseEntity.ok(reviewService.updateProduct(product));
    }

    @Override
    public void deleteProduct(Long id) {
          reviewService.deleteProduct(id);

    }
}

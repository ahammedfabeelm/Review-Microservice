package com.microservices.review.service.service;

import com.microservices.review.domain.Product;

import java.net.URISyntaxException;

/**
 * Service for managing {@link Product}.
 */
public interface ReviewService {

    Product getReview(String id);

    Product createProduct(Product product) throws URISyntaxException;

    Product updateProduct(Product product) throws URISyntaxException;

    void deleteProduct(Long id);

}

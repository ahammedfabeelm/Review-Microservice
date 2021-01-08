package com.microservices.review.service.service.impl;

import com.microservices.review.domain.Product;
import com.microservices.review.repository.ProductRepository;
import com.microservices.review.service.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation controller for managing {@link Product}.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ProductRepository productRepository;

    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

    public ReviewServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getReview(String id) {
        log.debug("REST request to get Product review : {}", id);
        Optional<Product> result = productRepository.findByProductId(id);
        return result.get();
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        log.debug("REST request to save Product : {}", product);
        if (product.getId() != null) {
            throw new IllegalArgumentException
                ("A new product cannot already have an ID");
        }
        if (productRepository.findByProductId(product.getProductId()).isPresent()) {
            throw new IllegalArgumentException("Product exists");
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        log.debug("REST request to update Product : {}", product);
        if (product.getProductId() == null) {
            throw new IllegalArgumentException("Invalid id");
        }
        if (!productRepository.findByProductId(product.getProductId()).isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }
        Optional<Product> result = productRepository.
            findByProductId(product.getProductId());
        result.ifPresent(value -> value.setAverageReviewScore(product.getAverageReviewScore()));
        return productRepository.save(result.get());
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        log.debug("REST request to delete Product : {}", id);

        if (id == null) {
            throw new IllegalArgumentException("Invalid id");
        }
        productRepository.deleteById(id);
    }
}


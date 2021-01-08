package com.microservices.review.web.rest.controller;

import com.microservices.review.domain.Product;
import com.microservices.review.service.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * REST controller for managing {@link Product}.
 */
@RequestMapping("/api")
public interface ReviewResource {

    /**
     * {@code GET  /review/:id} : get the review of a product.
     *
     * @param productId the id of the product to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     * and with body the product, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/review/{id}")
    ResponseEntity<Product> getReview(@PathVariable String productId);

    /**
     * {@code POST  /review} : Create a new review.
     *
     * @param product the review of product to create.
     * @return the {@link ResponseEntity}
     * with status {@code 201 (Created)} and with body the new product,
     * or with status {@code 400 (Bad Request)} if the product has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */

    @PostMapping("/review")
    ResponseEntity<Product> createProduct(@RequestBody Product product) throws URISyntaxException;

    /**
     * {@code PUT  /review} : Updates an existing review.
     *
     * @param product the Product to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated Product,
     * or with status {@code 400 (Bad Request)} if the productDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the Product couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/review")
    ResponseEntity<Product> updateProduct(@RequestBody Product product) throws URISyntaxException;

    /**
     * {@code DELETE  /review/:id} : delete the "id" of product.
     *
     * @param id the id of the product to delete.
     *           return with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/review/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteProduct(@PathVariable Long id);
}

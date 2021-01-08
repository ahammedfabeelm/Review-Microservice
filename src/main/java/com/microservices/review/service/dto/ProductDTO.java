package com.microservices.review.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.microservices.review.domain.Product} entity.
 */
public class ProductDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String productId;

    private Integer averageReviewScore;

    private Integer numberOfReview;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getAverageReviewScore() {
        return averageReviewScore;
    }

    public void setAverageReviewScore(Integer averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
    }

    public Integer getNumberOfReview() {
        return numberOfReview;
    }

    public void setNumberOfReview(Integer numberOfReview) {
        this.numberOfReview = numberOfReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        return id != null && id.equals(((ProductDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", productId='" + getProductId() + "'" +
            ", averageReviewScore=" + getAverageReviewScore() +
            ", numberOfReview=" + getNumberOfReview() +
            "}";
    }
}

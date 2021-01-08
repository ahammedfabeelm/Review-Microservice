package com.microservices.review.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "product_id", nullable = false, unique = true)
    private String productId;

    @Column(name = "average_review_score")
    private Integer averageReviewScore;

    @Column(name = "number_of_review")
    private Integer numberOfReview;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public Product productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getAverageReviewScore() {
        return averageReviewScore;
    }

    public Product averageReviewScore(Integer averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
        return this;
    }

    public void setAverageReviewScore(Integer averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
    }

    public Integer getNumberOfReview() {
        return numberOfReview;
    }

    public Product numberOfReview(Integer numberOfReview) {
        this.numberOfReview = numberOfReview;
        return this;
    }

    public void setNumberOfReview(Integer numberOfReview) {
        this.numberOfReview = numberOfReview;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", productId='" + getProductId() + "'" +
            ", averageReviewScore=" + getAverageReviewScore() +
            ", numberOfReview=" + getNumberOfReview() +
            "}";
    }
}

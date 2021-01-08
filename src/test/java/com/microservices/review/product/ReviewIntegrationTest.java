package com.microservices.review.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.review.ProductReviewApp;
import com.microservices.review.domain.Product;
import com.microservices.review.repository.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment =
    SpringBootTest.WebEnvironment.MOCK,
    classes = ProductReviewApp.class)
@AutoConfigureMockMvc
class ReviewIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ProductRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public Product setProduct() {
        Product product = new Product();
        product.setAverageReviewScore(133);
        product.setProductId("M1234");
        product.setNumberOfReview(12);
        return product;
    }

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity()) // enable security for the mock set up
            .build();
    }

    @WithMockUser(value = "admin", password = "admin")
    @Test
    void givenProduct_whenCreateReviews_thenStatus201() throws Exception {
        Product product = new Product();
        product.setAverageReviewScore(1);
        product.setProductId("M12345");
        product.setNumberOfReview(12);
        this.mvc.perform(post("/api/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.productId").value("M12345"));
    }

    @Test
    void givenProduct_whenCreateReviews_unAuthorized_thenStatus401() throws Exception {
        Product product = new Product();
        product.setAverageReviewScore(1);
        product.setProductId("M12");
        product.setNumberOfReview(12);
        this.mvc.perform(post("/api/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isUnauthorized());
    }

    @WithMockUser(value = "admin", password = "admin")
    @Test
    void givenProduct_whenGetReviews_thenStatus200()
        throws Exception {

        this.mvc.perform(post("/api/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(setProduct())))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.productId").value("M1234"));

        this.mvc.perform(get("/api/review/" + setProduct().getProductId())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").value("M1234"));
    }

    @Test
    void givenProduct_whenGetReviews_unAuthorized_thenStatus401() throws Exception {
        Product product = new Product();
        product.setAverageReviewScore(1);
        product.setProductId("M12");
        product.setNumberOfReview(12);
        this.mvc.perform(get("/api/review")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void givenProduct_whenUpdateReviews_unAuthorized_thenStatus401() throws Exception {
        Product product = new Product();
        product.setAverageReviewScore(1);
        product.setProductId("M12");
        product.setNumberOfReview(12);
        this.mvc.perform(put("/api/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isUnauthorized());
    }

    @WithMockUser(value = "admin", password = "admin")
    @Test
    void givenProduct_whenUpdateReviews_thenStatus200() throws Exception {
        Product product = new Product();
        product.setAverageReviewScore(33);
        product.setProductId("M123");
        product.setNumberOfReview(44);
        this.mvc.perform(post("/api/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.productId").value("M123"));

        product.setAverageReviewScore(66);
        this.mvc.perform(put("/api/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").value("M123"))
            .andExpect(jsonPath("$.averageReviewScore").value(66));
    }

    @WithMockUser(value = "admin", password = "admin")
    @Test
    void givenProduct_whenDeleteReview_thenStatus204() throws Exception {
        Product product = new Product();
        product.setAverageReviewScore(77);
        product.setProductId("M123456");
        product.setNumberOfReview(44);
        this.mvc.perform(post("/api/review")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.productId").value("M123456"));

        this.mvc.perform(delete("/api/review/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isNoContent());
    }

    @Test
    void givenProduct_whenDeleteReviews_unAuthorized_thenStatus401() throws Exception {
        Product product = new Product();
        product.setAverageReviewScore(1);
        product.setProductId("M12");
        product.setNumberOfReview(12);
        this.mvc.perform(delete("/api/review/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isUnauthorized());
    }
}

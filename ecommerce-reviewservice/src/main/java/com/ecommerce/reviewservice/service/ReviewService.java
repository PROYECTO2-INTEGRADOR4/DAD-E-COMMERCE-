package com.ecommerce.reviewservice.service;

import com.ecommerce.reviewservice.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {


    Review create(Review  review);
    Review updatePartial(Long id, Review reviewRequest);
    void delete(Long id);
    Optional<Review> read(Long id);
    List<Review> readAll();

}

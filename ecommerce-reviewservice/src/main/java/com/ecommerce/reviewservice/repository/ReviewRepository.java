package com.ecommerce.reviewservice.repository;

import com.ecommerce.reviewservice.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}

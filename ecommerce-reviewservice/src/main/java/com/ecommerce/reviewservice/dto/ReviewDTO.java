package com.ecommerce.reviewservice.dto;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    private Long id;

    private Long productoId;

    private String comment;

    private BigDecimal rating;

    private LocalDateTime fechaPublicacion;

}

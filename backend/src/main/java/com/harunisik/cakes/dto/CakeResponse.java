package com.harunisik.cakes.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CakeResponse {

    private Long id;
    private String name;
    private String description;
    private String createdBy;
    private LocalDateTime createdDate;

}

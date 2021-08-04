package com.harunisik.cakes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CakeUpdateRequest {

    private String name;
    private String description;
    private String createdBy;

}

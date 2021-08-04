package com.harunisik.cakes.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CakeCreateRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String createdBy;

}

package com.harunisik.cakes;

import com.harunisik.cakes.dto.CakeCreateRequest;
import com.harunisik.cakes.dto.CakeResponse;
import com.harunisik.cakes.dto.CakeUpdateRequest;
import com.harunisik.cakes.model.CakeEntity;
import java.time.LocalDateTime;

public class ObjectFactory {

    public static Long CAKE_ID = 1L;
    public static String NAME = "Cake 1";
    public static String DESCRIPTION = "Cake 1";
    public static String CREATED_BY = "John Lewis";
    public static LocalDateTime CREATED_DATE = LocalDateTime.now();
    public static String UPDATED_NAME = "Cake 2";
    public static String UPDATED_DESCRIPTION = "Cake 2";
    public static String UPDATED_CREATED_BY = "Bruce Lawyer";


    public static CakeResponse buildCakeResponse() {
        return CakeResponse.builder()
            .id(CAKE_ID)
            .name(NAME)
            .description(DESCRIPTION)
            .createdBy(CREATED_BY)
            .createdDate(CREATED_DATE)
            .build();
    }

    public static CakeCreateRequest buildCakeCreateRequest() {
        return CakeCreateRequest.builder()
            .name(NAME)
            .description(DESCRIPTION)
            .createdBy(CREATED_BY)
            .build();
    }

    public static CakeUpdateRequest buildCakeUpdateRequest() {
        return CakeUpdateRequest.builder()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_DESCRIPTION)
            .build();
    }

    public static CakeEntity buildCake() {
        return CakeEntity.builder()
            .id(CAKE_ID)
            .name(NAME)
            .description(DESCRIPTION)
            .createdBy(CREATED_BY)
            .createdDate(CREATED_DATE)
            .build();
    }
}

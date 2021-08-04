package com.harunisik.cakes.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.harunisik.cakes.dto.CakeCreateRequest;
import com.harunisik.cakes.dto.CakeResponse;
import com.harunisik.cakes.model.CakeEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface CakeMapper {

    CakeResponse toCakeResponse(final CakeEntity cakeEntity);

    CakeEntity toCake(final CakeCreateRequest cakeCreateRequest);
}

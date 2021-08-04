package com.harunisik.cakes.mapper;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.harunisik.cakes.ObjectFactory;
import com.harunisik.cakes.dto.CakeCreateRequest;
import com.harunisik.cakes.dto.CakeResponse;
import com.harunisik.cakes.model.CakeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CakeMapperTest {

    CakeMapperImpl customerAccountMapper = new CakeMapperImpl();

    @Test
    public void toCakeResponse() {
        CakeEntity cakeEntity = ObjectFactory.buildCake();
        CakeResponse cakeResponse = customerAccountMapper.toCakeResponse(cakeEntity);

        assertThat(cakeResponse.getId(), is(cakeEntity.getId()));
        assertThat(cakeResponse.getName(), is(cakeEntity.getName()));
        assertThat(cakeResponse.getDescription(), is(cakeEntity.getDescription()));
        assertThat(cakeResponse.getCreatedBy(), is(cakeEntity.getCreatedBy()));
        assertThat(cakeResponse.getCreatedDate(), is(cakeEntity.getCreatedDate()));
    }

    @Test
    public void toCake() {
        CakeCreateRequest cakeCreateRequest = ObjectFactory.buildCakeCreateRequest();
        CakeEntity cakeEntity = customerAccountMapper.toCake(cakeCreateRequest);

        assertThat(cakeEntity.getName(), is(cakeCreateRequest.getName()));
        assertThat(cakeEntity.getDescription(), is(cakeCreateRequest.getDescription()));
        assertThat(cakeEntity.getCreatedBy(), is(cakeCreateRequest.getCreatedBy()));
    }
}
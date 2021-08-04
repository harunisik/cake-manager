package com.harunisik.cakes.service;

import static com.harunisik.cakes.ObjectFactory.CAKE_ID;
import static com.harunisik.cakes.ObjectFactory.CREATED_BY;
import static com.harunisik.cakes.ObjectFactory.CREATED_DATE;
import static com.harunisik.cakes.ObjectFactory.DESCRIPTION;
import static com.harunisik.cakes.ObjectFactory.NAME;
import static com.harunisik.cakes.ObjectFactory.buildCake;
import static com.harunisik.cakes.ObjectFactory.buildCakeCreateRequest;
import static com.harunisik.cakes.ObjectFactory.buildCakeResponse;
import static com.harunisik.cakes.ObjectFactory.buildCakeUpdateRequest;
import static com.harunisik.cakes.exception.ExceptionType.CAKE_NOT_FOUND;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

import com.harunisik.cakes.dto.CakeCreateRequest;
import com.harunisik.cakes.dto.CakeResponse;
import com.harunisik.cakes.dto.CakeUpdateRequest;
import com.harunisik.cakes.exception.CakeManagerException;
import com.harunisik.cakes.mapper.CakeMapper;
import com.harunisik.cakes.model.CakeEntity;
import com.harunisik.cakes.repository.CakeRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.context.annotation.ComponentScan;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
@ComponentScan({"com.example.*"})
class CakeServiceTest {

    @InjectMocks
    private CakeService unit;

    @Mock
    private CakeRepository cakeRepository;

    @Mock
    private CakeMapper cakeMapper;

    @Test
    public void shouldGetAllCakes() {

        CakeEntity cakeEntity = buildCake();
        CakeResponse cakeResponse = buildCakeResponse();

        when(cakeRepository.findAll()).thenReturn(singletonList(cakeEntity));
        when(cakeMapper.toCakeResponse(cakeEntity)).thenReturn((cakeResponse));

        List<CakeResponse> cakeResponseList = unit.getAllCakes();

        CakeResponse result = cakeResponseList.get(0);

        verify(cakeRepository).findAll();
        verify(cakeMapper).toCakeResponse(cakeEntity);

        assertThat(result.getId(), comparesEqualTo(CAKE_ID));
        assertThat(result.getName(), comparesEqualTo(NAME));
        assertThat(result.getDescription(), comparesEqualTo(DESCRIPTION));
        assertThat(result.getCreatedBy(), comparesEqualTo(CREATED_BY));
        assertThat(result.getCreatedDate(), comparesEqualTo(CREATED_DATE));
    }

    @Test
    public void shouldGetCakeById() {

        CakeEntity cakeEntity = buildCake();
        CakeResponse cakeResponse = buildCakeResponse();

        when(cakeRepository.findById(CAKE_ID)).thenReturn(Optional.of(cakeEntity));
        when(cakeMapper.toCakeResponse(cakeEntity)).thenReturn((cakeResponse));

        CakeResponse result = unit.getCakeById(CAKE_ID);

        verify(cakeRepository).findById(CAKE_ID);
        verify(cakeMapper).toCakeResponse(cakeEntity);

        assertThat(result.getId(), comparesEqualTo(CAKE_ID));
        assertThat(result.getName(), comparesEqualTo(NAME));
        assertThat(result.getDescription(), comparesEqualTo(DESCRIPTION));
        assertThat(result.getCreatedBy(), comparesEqualTo(CREATED_BY));
        assertThat(result.getCreatedDate(), comparesEqualTo(CREATED_DATE));
    }

    @Test
    public void shouldThrowException_getCakeById_whenCakeNotFound() {

        CakeManagerException exceptionThrown = assertThrows(CakeManagerException.class,
            () -> unit.getCakeById(CAKE_ID));

        //THEN
        verify(cakeRepository).findById(CAKE_ID);

        assertThat(CAKE_NOT_FOUND.getMessage(), is(exceptionThrown.getMessage()));
        assertThat(CAKE_NOT_FOUND.getCode(), is(exceptionThrown.getExceptionType().getCode()));
    }

    @Test
    public void shouldCreateCake() {

        CakeEntity cakeEntity = buildCake();
        CakeCreateRequest cakeCreateRequest = buildCakeCreateRequest();
        CakeResponse cakeResponse = buildCakeResponse();

        when(cakeMapper.toCake(cakeCreateRequest)).thenReturn(cakeEntity);
        when(cakeMapper.toCakeResponse(cakeEntity)).thenReturn(cakeResponse);

        CakeResponse result = unit.createCake(cakeCreateRequest);

        verify(cakeMapper).toCake(cakeCreateRequest);
        verify(cakeRepository).save(cakeEntity);
        verify(cakeMapper).toCakeResponse(cakeEntity);

        assertThat(result.getId(), comparesEqualTo(CAKE_ID));
        assertThat(result.getName(), comparesEqualTo(NAME));
        assertThat(result.getDescription(), comparesEqualTo(DESCRIPTION));
        assertThat(result.getCreatedBy(), comparesEqualTo(CREATED_BY));
        assertThat(result.getCreatedDate(), comparesEqualTo(CREATED_DATE));
    }

    @Test
    public void shouldUpdateCake() {

        CakeEntity cakeEntity = buildCake();
        CakeUpdateRequest cakeUpdateRequest = buildCakeUpdateRequest();
        CakeResponse cakeResponse = buildCakeResponse();

        when(cakeRepository.findById(CAKE_ID)).thenReturn(Optional.of(cakeEntity));
        when(cakeMapper.toCakeResponse(cakeEntity)).thenReturn((cakeResponse));

        CakeResponse result = unit.updateCake(CAKE_ID, cakeUpdateRequest);

        verify(cakeRepository).findById(CAKE_ID);
        verify(cakeRepository).save(cakeEntity);
        verify(cakeMapper).toCakeResponse(cakeEntity);

        assertThat(result.getId(), comparesEqualTo(CAKE_ID));
        assertThat(result.getName(), comparesEqualTo(NAME));
        assertThat(result.getDescription(), comparesEqualTo(DESCRIPTION));
        assertThat(result.getCreatedBy(), comparesEqualTo(CREATED_BY));
        assertThat(result.getCreatedDate(), comparesEqualTo(CREATED_DATE));
    }

    @Test
    public void shouldThrowException_updateCake_whenCakeNotFound() {

        CakeManagerException exceptionThrown = assertThrows(CakeManagerException.class,
            () -> unit.updateCake(CAKE_ID, buildCakeUpdateRequest()));

        //THEN
        verify(cakeRepository).findById(CAKE_ID);

        assertThat(CAKE_NOT_FOUND.getMessage(), is(exceptionThrown.getMessage()));
        assertThat(CAKE_NOT_FOUND.getCode(), is(exceptionThrown.getExceptionType().getCode()));
    }

    @Test
    public void shouldDeleteCake() {

        CakeEntity cakeEntity = buildCake();
        CakeResponse cakeResponse = buildCakeResponse();

        when(cakeRepository.findById(CAKE_ID)).thenReturn(Optional.of(cakeEntity));
        when(cakeMapper.toCakeResponse(cakeEntity)).thenReturn((cakeResponse));

        CakeResponse result = unit.deleteCake(CAKE_ID);

        verify(cakeRepository).findById(CAKE_ID);
        verify(cakeRepository).delete(cakeEntity);
        verify(cakeMapper).toCakeResponse(cakeEntity);

        assertThat(result.getId(), comparesEqualTo(CAKE_ID));
        assertThat(result.getName(), comparesEqualTo(NAME));
        assertThat(result.getDescription(), comparesEqualTo(DESCRIPTION));
        assertThat(result.getCreatedBy(), comparesEqualTo(CREATED_BY));
        assertThat(result.getCreatedDate(), comparesEqualTo(CREATED_DATE));
    }

    @Test
    public void shouldThrowException_deleteCake_whenCakeNotFound() {

        CakeManagerException exceptionThrown = assertThrows(CakeManagerException.class, () -> unit.deleteCake(CAKE_ID));

        //THEN
        verify(cakeRepository).findById(CAKE_ID);

        assertThat(CAKE_NOT_FOUND.getMessage(), is(exceptionThrown.getMessage()));
        assertThat(CAKE_NOT_FOUND.getCode(), is(exceptionThrown.getExceptionType().getCode()));
    }
}
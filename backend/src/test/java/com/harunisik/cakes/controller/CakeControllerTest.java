package com.harunisik.cakes.controller;


import static com.harunisik.cakes.constant.UrlConstants.CREATE_CAKE_URL;
import static com.harunisik.cakes.constant.UrlConstants.DELETE_CAKE_URL;
import static com.harunisik.cakes.constant.UrlConstants.GET_ALL_CAKES_URL;
import static com.harunisik.cakes.constant.UrlConstants.GET_CAKE_BY_ID_URL;
import static com.harunisik.cakes.constant.UrlConstants.UPDATE_CAKE_URL;
import static com.harunisik.cakes.exception.ExceptionType.CAKE_NOT_FOUND;
import static com.harunisik.cakes.util.JsonUtils.objectToJson;
import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.harunisik.cakes.ObjectFactory;
import com.harunisik.cakes.dto.CakeCreateRequest;
import com.harunisik.cakes.dto.CakeResponse;
import com.harunisik.cakes.dto.CakeUpdateRequest;
import com.harunisik.cakes.exception.CakeManagerException;
import com.harunisik.cakes.exception.GlobalExceptionHandler;
import com.harunisik.cakes.service.CakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@ComponentScan({"com.example.*"})
public class CakeControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CakeController unit;

    @Mock
    private CakeService cakeService;

    public static String id = "id";
    public static String name = "name";
    public static String description = "description";
    public static String createdBy = "createdBy";
    public static String createdDate = "createdDate";
    public static String errorCode = "errorCode";
    public static String errorDescription = "description";
    public static final String JSON_EXPRESSION = "$['%s']";
    public static final String JSON_EXPRESSION2 = "$.%s[%s]['%s']";

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(unit)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
    }

    @Test
    public void shouldGetAllCakes() throws Exception {

        when(cakeService.getAllCakes()).thenReturn(singletonList(ObjectFactory.buildCakeResponse()));

        mockMvc.perform(get(GET_ALL_CAKES_URL))
            .andExpect(status().isOk())
            .andExpect(jsonPath(format(JSON_EXPRESSION2, "cakeList", 0, id)).value(ObjectFactory.CAKE_ID))
            .andExpect(jsonPath(format(JSON_EXPRESSION2, "cakeList", 0, name)).value(ObjectFactory.NAME))
            .andExpect(jsonPath(format(JSON_EXPRESSION2, "cakeList", 0, description)).value(ObjectFactory.DESCRIPTION))
            .andExpect(jsonPath(format(JSON_EXPRESSION2, "cakeList", 0, createdBy)).value(ObjectFactory.CREATED_BY));
    }

    @Test
    public void shouldGetCakeById() throws Exception {

        when(cakeService.getCakeById(ObjectFactory.CAKE_ID)).thenReturn(ObjectFactory.buildCakeResponse());

        mockMvc.perform(get(GET_CAKE_BY_ID_URL, "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath(format(JSON_EXPRESSION, id)).value(ObjectFactory.CAKE_ID))
            .andExpect(jsonPath(format(JSON_EXPRESSION, name)).value(ObjectFactory.NAME))
            .andExpect(jsonPath(format(JSON_EXPRESSION, description)).value(ObjectFactory.DESCRIPTION))
            .andExpect(jsonPath(format(JSON_EXPRESSION, createdBy)).value(ObjectFactory.CREATED_BY));
    }

    @Test
    public void shouldThrowException_getCakeById() throws Exception {

        doThrow(new CakeManagerException(CAKE_NOT_FOUND)).when(cakeService).getCakeById(any());

        this.mockMvc
            .perform(get(GET_CAKE_BY_ID_URL, "11"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath(format(JSON_EXPRESSION, errorCode)).value(CAKE_NOT_FOUND.getCode()))
            .andExpect(jsonPath(format(JSON_EXPRESSION, errorDescription)).value(CAKE_NOT_FOUND.getMessage()));
    }

    @Test
    public void shouldCreateCake() throws Exception {

        CakeCreateRequest cakeCreateRequest = ObjectFactory.buildCakeCreateRequest();

        when(cakeService.createCake(cakeCreateRequest)).thenReturn(ObjectFactory.buildCakeResponse());

        mockMvc.perform(post(CREATE_CAKE_URL)
            .content(objectToJson(cakeCreateRequest))
            .contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated())
            .andExpect(jsonPath(format(JSON_EXPRESSION, id)).value(ObjectFactory.CAKE_ID))
            .andExpect(jsonPath(format(JSON_EXPRESSION, name)).value(ObjectFactory.NAME))
            .andExpect(jsonPath(format(JSON_EXPRESSION, description)).value(ObjectFactory.DESCRIPTION))
            .andExpect(jsonPath(format(JSON_EXPRESSION, createdBy)).value(ObjectFactory.CREATED_BY));
    }

    @Test
    public void shouldUpdateCake() throws Exception {

        CakeUpdateRequest cakeUpdateRequest = ObjectFactory.buildCakeUpdateRequest();
        CakeResponse cakeResponse = ObjectFactory.buildCakeResponse();

        when(cakeService.updateCake(ObjectFactory.CAKE_ID, cakeUpdateRequest)).thenReturn(cakeResponse);

        mockMvc.perform(MockMvcRequestBuilders.put(UPDATE_CAKE_URL, ObjectFactory.CAKE_ID)
            .content(objectToJson(cakeUpdateRequest))
            .contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath(format(JSON_EXPRESSION, id)).value(ObjectFactory.CAKE_ID))
            .andExpect(jsonPath(format(JSON_EXPRESSION, name)).value(ObjectFactory.NAME))
            .andExpect(jsonPath(format(JSON_EXPRESSION, description)).value(ObjectFactory.DESCRIPTION))
            .andExpect(jsonPath(format(JSON_EXPRESSION, createdBy)).value(ObjectFactory.CREATED_BY));
    }

    @Test
    public void shouldThrowException_updateCake() throws Exception {

        doThrow(new CakeManagerException(CAKE_NOT_FOUND)).when(cakeService)
            .updateCake(anyLong(), any());

        this.mockMvc
            .perform(MockMvcRequestBuilders.put(UPDATE_CAKE_URL, ObjectFactory.CAKE_ID)
                .content(objectToJson(ObjectFactory.buildCakeUpdateRequest()))
                .contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath(format(JSON_EXPRESSION, errorCode)).value(CAKE_NOT_FOUND.getCode()))
            .andExpect(jsonPath(format(JSON_EXPRESSION, errorDescription)).value(CAKE_NOT_FOUND.getMessage()));
    }

    @Test
    public void shouldDeleteCake() throws Exception {

        CakeResponse cakeResponse = ObjectFactory.buildCakeResponse();

        when(cakeService.deleteCake(ObjectFactory.CAKE_ID)).thenReturn(cakeResponse);

        mockMvc.perform(MockMvcRequestBuilders.delete(DELETE_CAKE_URL, ObjectFactory.CAKE_ID)
            .contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath(format(JSON_EXPRESSION, id)).value(ObjectFactory.CAKE_ID))
            .andExpect(jsonPath(format(JSON_EXPRESSION, name)).value(ObjectFactory.NAME))
            .andExpect(jsonPath(format(JSON_EXPRESSION, description)).value(ObjectFactory.DESCRIPTION))
            .andExpect(jsonPath(format(JSON_EXPRESSION, createdBy)).value(ObjectFactory.CREATED_BY));
    }

    @Test
    public void shouldThrowException_deleteCake() throws Exception {

        doThrow(new CakeManagerException(CAKE_NOT_FOUND)).when(cakeService)
            .deleteCake(anyLong());

        this.mockMvc
            .perform(MockMvcRequestBuilders.delete(DELETE_CAKE_URL, ObjectFactory.CAKE_ID)
                .contentType(APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath(format(JSON_EXPRESSION, errorCode)).value(CAKE_NOT_FOUND.getCode()))
            .andExpect(jsonPath(format(JSON_EXPRESSION, errorDescription)).value(CAKE_NOT_FOUND.getMessage()));
    }

}

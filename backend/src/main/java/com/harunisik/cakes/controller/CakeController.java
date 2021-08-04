package com.harunisik.cakes.controller;

import static com.harunisik.cakes.constant.UrlConstants.CREATE_CAKE_URL;
import static com.harunisik.cakes.constant.UrlConstants.DELETE_CAKE_URL;
import static com.harunisik.cakes.constant.UrlConstants.GET_ALL_CAKES_URL;
import static com.harunisik.cakes.constant.UrlConstants.GET_CAKE_BY_ID_URL;
import static com.harunisik.cakes.constant.UrlConstants.UPDATE_CAKE_URL;
import static org.springframework.http.HttpStatus.CREATED;

import com.harunisik.cakes.dto.CakeCreateRequest;
import com.harunisik.cakes.dto.CakeListResponse;
import com.harunisik.cakes.dto.CakeResponse;
import com.harunisik.cakes.dto.CakeUpdateRequest;
import com.harunisik.cakes.service.CakeService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping
public class CakeController {

    private static final Logger logger = LoggerFactory.getLogger(CakeController.class);

    @Autowired
    private CakeService cakeService;

    @GetMapping(GET_ALL_CAKES_URL)
    public ResponseEntity<CakeListResponse> getAllCakes() {
        logger.info("get cakes called.");

        List<CakeResponse> cakeList = cakeService.getAllCakes();
        CakeListResponse cakeListResponse = CakeListResponse.builder()
            .cakeList(cakeList)
            .build();

        return ResponseEntity.ok().body(cakeListResponse);
    }

    @GetMapping(GET_CAKE_BY_ID_URL)
    public ResponseEntity<CakeResponse> getCakeById(@Valid @PathVariable(value = "id") String id) {
        logger.info("getCakeById api called.");
        CakeResponse cakeResponse = cakeService.getCakeById(Long.parseLong(id));
        return ResponseEntity.ok().body(cakeResponse);
    }

    @PostMapping(CREATE_CAKE_URL)
    public ResponseEntity<CakeResponse> createCake(@Valid @RequestBody CakeCreateRequest cakeCreateRequest) {
        logger.info("createCake api called.");
        CakeResponse cakeResponse = cakeService.createCake(cakeCreateRequest);
        return ResponseEntity.status(CREATED).body(cakeResponse);
    }

    @PutMapping(UPDATE_CAKE_URL)
    public ResponseEntity<CakeResponse> updateCake(
        @Valid @PathVariable(value = "id") String id,
        @Valid @RequestBody CakeUpdateRequest cakeUpdateRequest) {
        logger.info("updateCake api called.");

        CakeResponse cakeResponse = cakeService.updateCake(Long.parseLong(id), cakeUpdateRequest);
        return ResponseEntity.ok().body(cakeResponse);
    }

    @DeleteMapping(DELETE_CAKE_URL)
    public ResponseEntity<CakeResponse> deleteCake(@Valid @PathVariable(value = "id") String id) {
        logger.info("deleteCake api called.");

        CakeResponse cakeResponse = cakeService.deleteCake(Long.parseLong(id));
        return ResponseEntity.ok().body(cakeResponse);
    }
}
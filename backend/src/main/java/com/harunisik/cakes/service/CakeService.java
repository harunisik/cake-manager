package com.harunisik.cakes.service;

import static java.util.stream.Collectors.toList;

import com.harunisik.cakes.dto.CakeCreateRequest;
import com.harunisik.cakes.dto.CakeResponse;
import com.harunisik.cakes.dto.CakeUpdateRequest;
import com.harunisik.cakes.exception.CakeManagerException;
import com.harunisik.cakes.exception.ExceptionType;
import com.harunisik.cakes.mapper.CakeMapper;
import com.harunisik.cakes.model.CakeEntity;
import com.harunisik.cakes.repository.CakeRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CakeService {

    @Autowired
    private CakeRepository cakeRepository;

    @Autowired
    private CakeMapper cakeMapper;

    private static final Logger logger = LoggerFactory.getLogger(CakeService.class);

    @Transactional
    public List<CakeResponse> getAllCakes() {
        logger.info("getting all cakes from the db...");

        List<CakeEntity> cakeEntityList = cakeRepository.findAll();
        logger.info("{} cake(s) found in the db", cakeEntityList.size());

        return cakeEntityList.stream()
            .map(cakeMapper::toCakeResponse)
            .collect(toList());
    }

    @Transactional
    public CakeResponse getCakeById(Long id) {
        logger.info("getting cake with id {}...", id);

        Optional<CakeEntity> cake = cakeRepository.findById(id);

        if (cake.isPresent()) {
            logger.info("cake found with id {}", id);
            return cakeMapper.toCakeResponse(cake.get());
        }

        logger.info("cake not found with id {}", id);
        throw new CakeManagerException(ExceptionType.CAKE_NOT_FOUND);
    }

    @Transactional
    public CakeResponse createCake(CakeCreateRequest cakeCreateRequest) {
        logger.info("creating a new cake with the info {}...", cakeCreateRequest);

        CakeEntity cakeEntity = cakeMapper.toCake(cakeCreateRequest);
        cakeEntity.setCreatedDate(LocalDateTime.now());
        cakeRepository.save(cakeEntity);
        logger.info("cake created successfully with id {}", cakeEntity.getId());
        return cakeMapper.toCakeResponse(cakeEntity);
    }

    @Transactional
    public CakeResponse updateCake(Long id, CakeUpdateRequest cakeUpdateRequest) {
        logger.info("updating cake with id {} and info {}...", id, cakeUpdateRequest);

        Optional<CakeEntity> cakeOpt = cakeRepository.findById(id);
        if (cakeOpt.isPresent()) {
            logger.info("cake found with id {}", id);
            CakeEntity cakeEntity = cakeOpt.get();
            cakeEntity.setName(cakeUpdateRequest.getName());
            cakeEntity.setDescription(cakeUpdateRequest.getDescription());
            cakeEntity.setCreatedBy(cakeUpdateRequest.getCreatedBy());
            cakeRepository.save(cakeEntity);
            logger.info("cake updated successfully with id {}", id);
            return cakeMapper.toCakeResponse(cakeEntity);
        }

        throw new CakeManagerException(ExceptionType.CAKE_NOT_FOUND);
    }

    @Transactional
    public CakeResponse deleteCake(Long id) {
        logger.info("deleting cake with id {}...", id);

        Optional<CakeEntity> cakeOpt = cakeRepository.findById(id);
        if (cakeOpt.isPresent()) {
            logger.info("cake found with id {}", id);
            CakeEntity cakeEntity = cakeOpt.get();
            cakeRepository.delete(cakeEntity);
            logger.info("cake deleted successfully with id {}", id);
            return cakeMapper.toCakeResponse(cakeEntity);
        }

        throw new CakeManagerException(ExceptionType.CAKE_NOT_FOUND);
    }
}

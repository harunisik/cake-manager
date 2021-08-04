package com.harunisik.cakes.repository;

import com.harunisik.cakes.model.CakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CakeRepository extends JpaRepository<CakeEntity, Long> {

}

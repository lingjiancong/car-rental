package com.lingjiancong.car.repository;

import com.lingjiancong.car.model.CarProductDO;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lingjiancong
 * @since 2022-03-27
 */
public interface CarProductRepository extends JpaRepository<CarProductDO, Long> {
}

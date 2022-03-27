package com.lingjiancong.car.repository;

import com.lingjiancong.car.model.CarDO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author lingjiancong
 * @since 2022-03-27
 */
public interface CarRepository extends JpaRepository<CarDO, Long> {

    @Query(value = "SELECT car_id, car_no, product_id FROM car  WHERE car_no = :carNo", nativeQuery = true)
    CarDO getByCarNo(@Param("carNo") String carNo);


}

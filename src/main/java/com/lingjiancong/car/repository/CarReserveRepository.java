package com.lingjiancong.car.repository;

import com.lingjiancong.car.model.CarReserveDO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author lingjiancong
 * @since 2022-03-27
 */
public interface CarReserveRepository extends JpaRepository<CarReserveDO, Long> {


    /**
     * 按预约日期查询
     */
    @Query(value = "SELECT reserve_id, car_no, user_id, reserve_start_time, reserve_end_time FROM car_reserve  " +
            "WHERE reserve_start_time >= :reserveDate and reserve_end_time < :reserveDate", nativeQuery = true)
    List<CarReserveDO> listByReserveDate(@Param("reserveDate") String reserveDate);
}

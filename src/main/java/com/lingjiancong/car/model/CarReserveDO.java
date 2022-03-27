package com.lingjiancong.car.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lingjiancong
 * @since 2022-03-27
 */
@Entity
@Table(name = "car_reserve")
public class CarReserveDO {

    private Long reserveId;

    private String carNo;

    private Long userId;

    private String reserveStartTime;

    private String getReserveEndTime;

    @Id
    @Column(name = "reserve_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getReserveId() {
        return reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    @Column(name = "car_no", nullable = false)
    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "reserve_start_time", nullable = false)
    public String getReserveStartTime() {
        return reserveStartTime;
    }

    public void setReserveStartTime(String reserveStartTime) {
        this.reserveStartTime = reserveStartTime;
    }

    @Column(name = "reserve_end_time", nullable = false)
    public String getGetReserveEndTime() {
        return getReserveEndTime;
    }

    public void setGetReserveEndTime(String getReserveEndTime) {
        this.getReserveEndTime = getReserveEndTime;
    }
}

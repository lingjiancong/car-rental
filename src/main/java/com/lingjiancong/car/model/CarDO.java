package com.lingjiancong.car.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 汽车
 * @author lingjiancong
 * @since 2022-03-27
 */
@Entity
@Table(name = "car")
public class CarDO {

    private Long carId;

    private String carNo;

    private Long productId;

    public CarDO() {
    }

    public CarDO(String carNo, Long productId) {
        this.carNo = carNo;
        this.productId = productId;
    }

    @Id
    @Column(name = "car_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Column(name = "car_no", nullable = false)
    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @Column(name = "product_id", nullable = false)
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

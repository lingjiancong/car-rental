package com.lingjiancong.car.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 卡商品
 * @author lingjiancong
 * @since 2022-03-27
 */
@Entity
@Table(name = "car_product")
public class CarProductDO {

    private Long productId;

    private String carProductNo;

    private String carModel;

    private Long stock;

    public CarProductDO() {
    }

    public CarProductDO(Long productId, String carProductNo, String carModel, Long stock) {
        this.productId = productId;
        this.carProductNo = carProductNo;
        this.carModel = carModel;
        this.stock = stock;
    }

    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Column(name = "car_product_no", nullable = false)
    public String getCarProductNo() {
        return carProductNo;
    }

    public void setCarProductNo(String carProductNo) {
        this.carProductNo = carProductNo;
    }

    @Column(name = "car_model", nullable = false)
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Column(name = "stock", nullable = false)
    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}

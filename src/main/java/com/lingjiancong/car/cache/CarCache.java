package com.lingjiancong.car.cache;

import com.lingjiancong.car.model.CarDO;
import com.lingjiancong.car.model.CarProductDO;
import com.lingjiancong.car.repository.CarProductRepository;
import com.lingjiancong.car.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lingjiancong
 * @since 2022-03-27
 */
@Component
public class CarCache {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarProductRepository carProductRepository;

    /**
     * 获取 car
     */
    public CarDO getCarByCarNo(String carNo) {
        return carRepository.getByCarNo(carNo);
    }

    /**
     * 获取 productId
     */
    public CarProductDO getCarProductByProductId(long productId) {
        return carProductRepository.getOne(productId);
    }


}

package com.lingjiancong.car.controller;

import com.lingjiancong.car.model.CarDO;
import com.lingjiancong.car.model.CarProductDO;
import com.lingjiancong.car.repository.CarProductRepository;
import com.lingjiancong.car.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 初始化
 * @author lingjiancong
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private CarProductRepository carProductRepository;

    @Autowired
    private CarRepository carRepository;

    /**
     * 获取可预约时段
     */
    @GetMapping("/data")
    public ResponseEntity<String> initData() {
        CarProductDO carProductDO1 = new CarProductDO(1L, "ToyotaCamry", "Toyota Camr", 2L);
        CarProductDO carProductDO2 = new CarProductDO(2L, "BMW650", "BMW 650", 2L);
        carProductRepository.save(carProductDO1);
        carProductRepository.save(carProductDO2);

        CarDO carDO1 = new CarDO("ToyotaCamry1", 1L);
        CarDO carDO2 = new CarDO("ToyotaCamry2", 1L);
        CarDO carDO3 = new CarDO("BMW6501", 2L);
        CarDO carDO4 = new CarDO("BMW6502", 2L);
        carRepository.save(carDO1);
        carRepository.save(carDO2);
        carRepository.save(carDO3);
        carRepository.save(carDO4);
        return ResponseEntity.ok().body("success");
    }
}

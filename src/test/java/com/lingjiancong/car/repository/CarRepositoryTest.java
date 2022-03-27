package com.lingjiancong.car.repository;

import com.lingjiancong.car.Application;
import com.lingjiancong.car.model.CarDO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lingjiancong
 * @since 2022-03-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testInsertAndGet() {
        CarDO carDO = new CarDO();
        String carNo = "carNo";
        long productId = 1L;
        carDO.setCarNo(carNo);
        carDO.setProductId(productId);

        carRepository.save(carDO);

        CarDO byCarNo = carRepository.getByCarNo(carNo);
        Assert.assertTrue(byCarNo != null);
        Assert.assertTrue(byCarNo.getProductId() != null);
        Assert.assertTrue(byCarNo.getProductId() == productId);
    }



}

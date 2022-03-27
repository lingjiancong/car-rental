package com.lingjiancong.car.controller;

import com.lingjiancong.car.bo.CarReserveTimeBo;
import com.lingjiancong.car.cache.CarCache;
import com.lingjiancong.car.model.CarDO;
import com.lingjiancong.car.model.CarProductDO;
import com.lingjiancong.car.vo.req.CarReserveTimeReqVO;
import com.lingjiancong.car.vo.resp.CarReserveTimeRespVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author lingjiancong
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/api/v1")
@Api(value="car reserve")
public class CarReserveController {

    @Autowired
    private CarReserveTimeBo carReserveTimeBo;

    @Autowired
    private CarCache carCache;

    /**
     * 获取可预约时段
     */
    @ApiOperation(value = "View a car's available reserve time", response = CarReserveTimeRespVO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved")})
    @GetMapping("/carReserveTime/{carNo}")
    public ResponseEntity<CarReserveTimeRespVO> getCarReserveTime(
            @ApiParam(value = "carNo", required = true)
            @PathVariable(value = "carNo") String carNo) {
        CarDO byCarNo = carCache.getCarByCarNo(carNo);
        if (byCarNo == null) {
            throw new IllegalArgumentException("car doesn't exist");
        }
        CarProductDO carProduct = carCache.getCarProductByProductId(byCarNo.getProductId());
        CarReserveTimeRespVO reserveTimeRespVO = new CarReserveTimeRespVO();
        reserveTimeRespVO.setCarNo(carNo);
        reserveTimeRespVO.setCarModel(carProduct.getCarModel());
        reserveTimeRespVO.setAvailableTimes(carReserveTimeBo.getCarReserveTime(carNo));
        return ResponseEntity.ok().body(reserveTimeRespVO);
    }

    /**
     * 预约时段
     */
    @PostMapping("/carReserveTime")
    public ResponseEntity<Boolean> carReserveTime(
            @ApiParam(value = "car reverseTime request", required = true)
            @Valid @RequestBody CarReserveTimeReqVO req) {
        CarDO byCarNo = carCache.getCarByCarNo(req.getCarNo());
        if (byCarNo == null) {
            throw new IllegalArgumentException("car doesn't exist");
        }
        long userId = 9L;
        boolean res = carReserveTimeBo.reserveCarTime(req.getCarNo(), userId, req.getScheduleDate(), req.getStartTime(), req.getCount());
        return ResponseEntity.ok().body(res);
    }

}

package com.lingjiancong.car.vo.resp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * car 预约时间
 * @author lingjiancong
 * @since 2022-03-27
 */
@ApiModel(description="car 可预约时间")
public class CarReserveTimeRespVO implements Serializable {

     private static final long serialVersionUID = 1L;

     /**
      * car 编号
      */
     @ApiModelProperty(notes = "car编号")
     private String carNo;

     /**
      * car 型号
      */
     @ApiModelProperty(notes = "car型号")
     private String carModel;


     /**
      * 空闲时间，预约时段半小时起约
      * 一天 24 小时
      * 0000 - 0030 -- -- 2330
      * 共有 48 个半小时时段
      * 返回 日期 -> 空闲时间
      */
     @ApiModelProperty(notes = "0000 - 0030 -- -- 2330，预约时段半小时起约, 一天 24 小时，共有 48 个半小时时段")
     private Map<String, List<Integer>> availableTimes;

     public String getCarNo() {
          return carNo;
     }

     public void setCarNo(String carNo) {
          this.carNo = carNo;
     }

     public String getCarModel() {
          return carModel;
     }

     public void setCarModel(String carModel) {
          this.carModel = carModel;
     }

     public Map<String, List<Integer>> getAvailableTimes() {
          return availableTimes;
     }

     public void setAvailableTimes(Map<String, List<Integer>> availableTimes) {
          this.availableTimes = availableTimes;
     }

     @Override
     public String toString() {
          return "CarReserveTimeVO{" +
                  "carNo='" + carNo + '\'' +
                  ", carModel='" + carModel + '\'' +
                  ", availableTimes=" + availableTimes +
                  '}';
     }
}

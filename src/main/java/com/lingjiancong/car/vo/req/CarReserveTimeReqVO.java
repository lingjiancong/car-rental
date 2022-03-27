package com.lingjiancong.car.vo.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * car 预约请求
 * @author lingjiancong
 * @since 2022-03-27
 */
@ApiModel(description="car 预约时段请求")
public class CarReserveTimeReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * car 编号
     */
    @ApiModelProperty(notes = "car编号")
    private String carNo;

    /**
     * 预约天数
     * 不允许跨天预约
     */
    @ApiModelProperty(notes = "预约日期")
    private String scheduleDate;

    /**
     * 空闲时间，预约时段半小时起约
     * 一天 24 小时
     * 0000 - 0030 -- -- 2330
     * 共有 48 个半小时时段
     */
    @ApiModelProperty(notes = "0000 - 0030 -- -- 2330，预约时段半小时起约, 一天 24 小时，共有 48 个半小时时段")
    private Integer startTime;

    /**
     * 从 startTime 开始预约了几个半小时时段
     */
    @ApiModelProperty(notes = "从 startTime 开始预约了几个半小时时段")
    private Integer count;

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @Override
    public String toString() {
        return "CarReserveTimeReqVO{" +
                "carNo='" + carNo + '\'' +
                ", scheduleDate='" + scheduleDate + '\'' +
                ", startTime=" + startTime +
                ", count=" + count +
                '}';
    }
}

package com.lingjiancong.car.bo;

import com.lingjiancong.car.cache.CarReserveTimeCache;
import com.lingjiancong.car.model.CarReserveDO;
import com.lingjiancong.car.repository.CarReserveRepository;
import com.lingjiancong.car.util.LocalDateTimeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 汽车预约 bo
 * @author lingjiancong
 * @since 2022-03-27
 */
@Component
public class CarReserveTimeBo {

    /**
     * 可预约的天数
     */
    public static final int AVAILABLE_DAYS = 3;

    @Autowired
    private CarReserveTimeCache carReserveTimeCache;

    @Autowired
    private CarReserveRepository carReserveRepository;

    /**
     * 返回 car 的可预约时段
     * 只返回 三天
     * @return key -> date, value -> 0000 - 0030 -- -- 2330 共有 48 个半小时时段
     */
    public Map<String, List<Integer>> getCarReserveTime(String carNo) {
        Map<String, List<Integer>> carReserveTimeMap = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < AVAILABLE_DAYS; ++i) {
            String date = now.plusDays(i).toLocalDate().toString();
            // time index 0 - 47
            List<Integer> reserveTime = carReserveTimeCache.getReserveTime(carNo, date);
            List<Integer> realTimeArr = new ArrayList<>();
            for (int index = 0; index < reserveTime.size(); ++index) {
                if (reserveTime.get(index) == 0) {
                    int hour = index / 2;
                    int min = index % 2;
                    // 格式 1030
                    int realTime = hour * 100 + min * 30;
                    realTimeArr.add(realTime);
                }
            }
            carReserveTimeMap.put(date, realTimeArr);
        }
        return carReserveTimeMap;
    }

    /**
     * 预约时段
     * @param carNo car 编号
     * @param date 日期
     * @param startTime  格式 1030
     * @param count 预约时段次数
     * @return
     */
    public boolean reserveCarTime(String carNo, long userId, String date, int startTime, int count) {
        int hour = startTime / 100;
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("startTime invalid " + startTime);
        }
        int mins = startTime % 100;
        if (mins != 0 && mins != 30) {
            throw new IllegalArgumentException("startTime invalid " + startTime);
        }
        int index = hour * 2 + mins / 30;
        int endIndex = index + count;
        if (count <= 0 || endIndex > 24) {
            throw new IllegalArgumentException("time invalid " + startTime + " | " + count);
        }
        List<Integer> timeSlots = new ArrayList<>();
        for (int i = index; i < endIndex; ++i) {
            timeSlots.add(i);
        }
        boolean res = carReserveTimeCache.reserveTime(carNo, date, timeSlots);
        if (res) {
            // 入库
            CarReserveDO carReserveDO = new CarReserveDO();
            carReserveDO.setCarNo(carNo);
            carReserveDO.setUserId(userId);
            // todo check
            LocalDateTime startDateTime = LocalDateTimeUtils.parseDate(date).plusHours(hour).plusMinutes(mins);
            LocalDateTime endDateTime = startDateTime.plusMinutes(count * 30);
            carReserveDO.setReserveStartTime(startDateTime.toString());
            carReserveDO.setGetReserveEndTime(endDateTime.toString());
            carReserveRepository.save(carReserveDO);
        }

        return res;

    }
}

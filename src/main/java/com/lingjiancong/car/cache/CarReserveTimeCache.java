package com.lingjiancong.car.cache;

import com.lingjiancong.car.model.CarReserveDO;
import com.lingjiancong.car.repository.CarReserveRepository;
import com.lingjiancong.car.util.LocalDateTimeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * car 预约时间缓存
 * @author lingjiancong
 * @since 2022-03-27
 */
@Component
public class CarReserveTimeCache {


    private static final int TIME_SIZE = 48;
    
    @Autowired
    private CarReserveRepository carReserveRepository;

    /**
     * key -> carNo + scheduleDate
     * value -> 48 个数字，表示 48 个半小时时段，0000 -> 2330, 为 1 代表该时段已被预约，为 0 代表该时段未被预约
     */
    private ConcurrentHashMap<String, List<Integer>> reserveTimeMap = new ConcurrentHashMap<>();

    /**
     * 获取预约时段
     */
    public List<Integer> getReserveTime(String carNo, String reserveDate) {
        String key = getKey(carNo, reserveDate);
        List<Integer> reserveTime = reserveTimeMap.get(key);
        if (reserveTime == null) {
            // 初始化
            initReserveTimeMap(carNo, reserveDate);
        }
        return reserveTimeMap.get(key);
    }

    /**
     * 单机版使用 synchronized, 多机版使用分布式锁
     */
    private synchronized void initReserveTimeMap(String carNo, String reserveDate) {
        String key = getKey(carNo, reserveDate);
        List<Integer> reserveTime = reserveTimeMap.get(key);
        if (reserveTime != null) {
            return;
        }
        List<CarReserveDO> carReserveDOS = carReserveRepository.listByReserveDate(reserveDate);
        reserveTime = new ArrayList<>(TIME_SIZE);
        // 初始化
        for (int i = 0; i < TIME_SIZE; ++i) {
            reserveTime.add(0);
        }
        // 找出最小和最大时间
        String minTime = "2099-01-01T23:59:59";
        String maxTime = "0000-01-01T00:00:00";
        for (CarReserveDO carReserveDO : carReserveDOS) {
            String reserveStartTime = carReserveDO.getReserveStartTime();
            String reserveEndTime = carReserveDO.getGetReserveEndTime();
            if (minTime.compareTo(reserveStartTime) > 0) {
                minTime = reserveStartTime;
            }
            if (maxTime.compareTo(reserveEndTime) < 0) {
                maxTime = reserveEndTime;
            }
        }
        LocalDateTime minDateTime = LocalDateTime.parse(minTime);
        LocalDateTime maxDateTime = LocalDateTime.parse(maxTime);
        // 做初始化

        for (int i = 0; i < TIME_SIZE; ++i) {
            LocalDateTime localDateTime = LocalDateTimeUtils.parseDate(reserveDate).plusMinutes(i * 30);
            // 已被人预约
            if (minDateTime.compareTo(localDateTime) <= 0 && maxDateTime.compareTo(localDateTime) > 0) {
                reserveTime.set(i, 1);
            }
        }
        reserveTimeMap.put(key, reserveTime);
    }

    /**
     * 预约时段
     * todo 使用分布式锁 或者 使用 redis lua script
     */
    public synchronized boolean reserveTime(String carNo, String reserveDate, List<Integer> timeOffsets) {
        List<Integer> reserveTime = getReserveTime(carNo, reserveDate);
        // 先检查一次
        for (Integer timeOffset : timeOffsets) {
            if (reserveTime.get(timeOffset) == 1) {
                throw new IllegalArgumentException("时段已被预约");
            }
        }
        // 再设置
        for (Integer timeOffset : timeOffsets) {
            reserveTime.set(timeOffset, 1);
        }
        return true;
    }

    /**
     * 获取 key
     */
    private String getKey(String carNo, String reserveDate) {
        return carNo + ":" + reserveDate;
    }
}

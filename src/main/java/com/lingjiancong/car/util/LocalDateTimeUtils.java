package com.lingjiancong.car.util;

import java.time.LocalDateTime;

/**
 * @author lingjiancong
 * @since 2022-03-27
 */
public class LocalDateTimeUtils {

    /**
     * date -> localDateTime
     */
    public static LocalDateTime parseDate(String date) {
        return LocalDateTime.parse(date + "T00:00:00");
    }

}

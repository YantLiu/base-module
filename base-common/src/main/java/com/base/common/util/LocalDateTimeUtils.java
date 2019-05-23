package com.base.common.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author
 * @description LocalDateTime Date 转换类
 * @date: 2018/12/21
 */
@Slf4j
public class LocalDateTimeUtils {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    private LocalDateTimeUtils() {
    }

    //java.util.Date --> java.time.LocalDateTime
    public static LocalDateTime convertDate2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    //java.time.LocalDateTime --> java.util.Date
    public static Date convertLocalDateTime2Date(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * @param localDateTime 时间
     * @return java.lang.String
     * @description 格式化 LocalDateTime
     * @author liuyanting
     * @date 2018/12/21
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * @param date    时间
     * @param pattern 正则
     * @return java.lang.String
     * @description 格式化 Date
     * @author liuyanting
     * @date 2018/12/21
     */
    public static String formatDate(Date date, String pattern) {
        LocalDateTime localDateTime = convertDate2LocalDateTime(date);
        return formatLocalDateTime(localDateTime, pattern);
    }

    /**
     * @param str yyyy-MM-dd HH:mm:ss 格式字符串
     * @return LocalDateTime
     * @description 字符串 解析成 LocalDateTime
     * @author liuyanting
     * @date 2018/12/21
     */
    public static LocalDateTime parseLocalDateTime(String str, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(str, formatter);
        } catch (Exception e1) {
            try {
                return LocalDate.parse(str, formatter).atStartOfDay();
            } catch (Exception e2) {
                log.error("[{}]无法解析成[{}]时间", str, pattern);
                return null;
            }
        }
    }

    /**
     * @param str yyyy-MM-dd HH:mm:ss 格式字符串
     * @return Date
     * @description 字符串 解析成 Date
     * @author liuyanting
     * @date 2018/12/21
     */
    public static Date parseDate(String str, String pattern) {
        LocalDateTime localDateTime = parseLocalDateTime(str, pattern);
        if (null == localDateTime) {
            return null;
        }
        return convertLocalDateTime2Date(localDateTime);
    }
}

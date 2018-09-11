package com.xxl.job.admin.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: xiaoxu.nie
 * @date: 2018-09-07 9:41
 */
@Slf4j
public class DateUtil {

    public static Date dateUtc(String dateString) {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        dateString = dateString.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            log.error("DateUtc:{}", e);
        }
        return null;
    }

    /**
     * 获取当前日期的前一个月日期
     *
     * @return
     */
    public static Date getAgoMonthDay() {
        Calendar calendar = Calendar.getInstance();
        //获取当前日期一个月前的日期
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

   /* public static void main(String[] args) {
        Date date = DateUtil.getAgoMonthDay();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
    }*/
}

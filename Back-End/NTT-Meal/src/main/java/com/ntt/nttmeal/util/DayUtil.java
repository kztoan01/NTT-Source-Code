package com.ntt.nttmeal.util;

import java.time.Instant;

public class DayUtil {
    public static String changeTime(Instant day,String time) {
        String dayString = day.toString();
        String[] days = dayString.split("T");
        return days[0]+"T"+time+"Z";
    }

}

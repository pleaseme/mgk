package com.example.jpademo.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date getFormatDate(Date date)
    {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(date);
        try {
            Date parse = format.parse(format1);
            System.out.println(parse);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }  


}

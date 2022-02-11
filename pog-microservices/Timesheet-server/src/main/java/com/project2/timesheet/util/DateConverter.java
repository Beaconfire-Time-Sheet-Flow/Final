package com.project2.timesheet.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DateConverter {

    public List<String> getDatesFromWeekEnding(String weekEnding, int offset) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = sdf.parse(weekEnding);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        List<String> weekdayList = new ArrayList<>();

        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < offset; i++) {
            weekdayList.add(dateformat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, -1);
        }

        Collections.reverse(weekdayList);
        return weekdayList;
    }
}

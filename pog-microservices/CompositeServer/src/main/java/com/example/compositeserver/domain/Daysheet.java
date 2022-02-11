package com.example.compositeserver.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Daysheet {

    private String day;
    private String date;
    private int startTime;
    private int endTime;
    private double hours;
    private boolean isFloating;
    private boolean isHoliday;
    private boolean isVacation;
}

package com.project2.timesheet.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DaysheetDTO {

    private String day;
    private String date;
    private String startTime;
    private String endTime;
    private int hours;
    private boolean ifFloating;
    private boolean ifHoliday;
    private boolean ifVacation;
}


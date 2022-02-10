package com.project2.timesheet.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Daysheet {

    private String day;
    private String date;
    private String startTime;
    private String endTime;
    private double hours;
    private boolean isFloating;
    private boolean isHoliday;
    private boolean isVacation;
}

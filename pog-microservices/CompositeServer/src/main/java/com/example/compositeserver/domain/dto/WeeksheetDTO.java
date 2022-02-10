package com.example.compositeserver.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeeksheetDTO {
    private String day;
    private String date;
    private String startTime;
    private String endTime;
    private int totalHours;
    private boolean ifFloating;
    private boolean ifHoliday;
    private boolean ifVacation;
}

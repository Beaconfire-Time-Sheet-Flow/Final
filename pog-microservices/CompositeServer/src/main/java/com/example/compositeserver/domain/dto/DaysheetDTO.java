package com.example.compositeserver.domain.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DaysheetDTO {
    private String day;
    private String date;
    private int startTime;
    private int endTime;
    private int totalHours;
    private boolean ifFloating;
    private boolean ifHoliday;
    private boolean ifVacation;
}

package com.project2.timesheet.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeeksheetDTO {

    private String weekEnding;
    private int totalBillingHours;
    private int totalCompensatedHours;
    private String submissionStatus;
    private String approvalStatus;
    private String comment;
}

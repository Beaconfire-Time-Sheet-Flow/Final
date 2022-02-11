package com.project2.timesheet.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Weeksheet {

    private String weekEnding;

    private int totalBillingHours;

    private int totalCompensatedHours;

    private String submissionStatus;

    private String approvalStatus;

    private int floatingDaysLeft;
    
    private int vacationDaysLeft;
    
    private String fileURL;

    private List<Daysheet> days;

}
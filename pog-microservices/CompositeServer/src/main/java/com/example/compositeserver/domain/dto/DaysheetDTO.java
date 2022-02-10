package com.example.compositeserver.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DaysheetDTO {
    private String weekEnding;
    private int totalBillingHours;
    private int totalCompensatedHours;
    private String submissionStatus;
    private String approvalStatus;
    private String comment;
}

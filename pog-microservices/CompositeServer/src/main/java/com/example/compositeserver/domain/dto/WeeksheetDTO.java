package com.example.compositeserver.domain.dto;

import lombok.*;

@Getter
@Setter
@Data
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
    private String fileURL;
}

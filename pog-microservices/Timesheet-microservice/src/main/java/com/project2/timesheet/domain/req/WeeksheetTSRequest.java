package com.project2.timesheet.domain.req;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeeksheetTSRequest {
    private int userId;
    private String weekEnding;
}

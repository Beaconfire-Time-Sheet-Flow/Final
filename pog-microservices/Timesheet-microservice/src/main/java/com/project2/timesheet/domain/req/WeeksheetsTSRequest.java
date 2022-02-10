package com.project2.timesheet.domain.req;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeeksheetsTSRequest {
    private int userId;
}

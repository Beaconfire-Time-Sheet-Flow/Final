package com.project2.timesheet.domain.res;

import com.project2.timesheet.domain.dto.DaysheetDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeeksheetTSResponse {
    private String weekEnding;
    private int totalBillingHours;
    private int totalCompensatedHours;
    private List<DaysheetDTO> daysheetDTOS;
}

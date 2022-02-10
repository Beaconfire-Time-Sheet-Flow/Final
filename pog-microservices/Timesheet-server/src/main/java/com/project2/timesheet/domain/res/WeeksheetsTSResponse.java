package com.project2.timesheet.domain.res;

import com.project2.timesheet.domain.dto.WeeksheetDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeeksheetsTSResponse {
    private List<WeeksheetDTO> weeksheetDTOS;
}

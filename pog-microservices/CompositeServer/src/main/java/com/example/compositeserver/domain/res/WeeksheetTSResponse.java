package com.example.compositeserver.domain.res;

import com.example.compositeserver.domain.dto.DaysheetDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
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

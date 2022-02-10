package com.example.compositeserver.domain.res;

import com.example.compositeserver.domain.dto.WeeksheetDTO;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeeksheetsTSResponse {
    private List<WeeksheetDTO> weeksheetDTOS;
}

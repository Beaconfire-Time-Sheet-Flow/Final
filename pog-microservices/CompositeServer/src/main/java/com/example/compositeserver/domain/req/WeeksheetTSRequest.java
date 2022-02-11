package com.example.compositeserver.domain.req;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeeksheetTSRequest {
    private int userId;
    private String weekEnding;

}

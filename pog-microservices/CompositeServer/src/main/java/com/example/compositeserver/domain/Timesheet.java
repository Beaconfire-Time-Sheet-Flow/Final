package com.example.compositeserver.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
//@QueryEntity
//@Document(collection = "weeksheet")
public class Timesheet {

//    @Id
    private String id;

    private int userId;
    private List<Daysheet> days;
    private List<Weeksheet> weeks;



}

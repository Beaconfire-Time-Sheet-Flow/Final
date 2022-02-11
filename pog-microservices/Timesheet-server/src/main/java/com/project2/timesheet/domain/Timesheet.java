package com.project2.timesheet.domain;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@QueryEntity
@Document(collection = "weeksheet")
public class Timesheet {

    @Id
    private String id;

    private int userId;
    private List<Daysheet> days;
    private List<Weeksheet> weeks;



}

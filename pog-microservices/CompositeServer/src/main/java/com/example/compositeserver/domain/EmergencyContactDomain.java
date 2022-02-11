package com.example.compositeserver.domain;

import lombok.*;

@Getter
@Setter
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContactDomain {
    private Integer id;

    private String firstName;

    private String lastName;

    private String cellPhone;
}

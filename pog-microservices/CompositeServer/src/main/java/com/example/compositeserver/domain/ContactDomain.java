package com.example.compositeserver.domain;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDomain {
    private Integer id;

    private String cellPhone;

    private String email;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String zipCode;

    private Integer account_id;
}
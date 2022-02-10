package com.example.compositeserver.domain;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDomain {
    private ContactDomain contactDomain;

    private EmergencyContactDomain emergencyContactDomain;
}

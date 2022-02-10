package com.example.employeemicroservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="contact")
@Getter
@Setter
@NoArgsConstructor
public class Contact implements Serializable {

    @Id
    private Integer id;

    @Column(name = "cellphone")
    private String cellPhone;

    @Column(name = "email")
    private String email;

    @Column(name = "addressline1")
    private String addressLine1;

    @Column(name = "addressline2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipCode;

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}

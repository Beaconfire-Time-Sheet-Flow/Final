package com.example.employeemicroservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="account")
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    @JsonIgnore
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
    @JsonIgnore
    private EmergencyContact emergencyContact;

}

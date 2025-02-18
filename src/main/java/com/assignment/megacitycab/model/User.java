package com.assignment.megacitycab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class User {

    @Id
    private Long id;
    private String username;
    private String password;
    private String role;


}

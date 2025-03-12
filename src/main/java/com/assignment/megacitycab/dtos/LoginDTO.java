package com.assignment.megacitycab.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
}

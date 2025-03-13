package com.assignment.megacitycab.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customers")
public class Customer extends User {
    public String getName() {
        return super.getName();
    }

    public Long getId(){
        return this.id;
    }

    public void setName(String name) {
        super.setName(name);
    }
}
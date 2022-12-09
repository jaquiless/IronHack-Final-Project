package com.finalProject.demo.models.Users;

import com.finalProject.demo.models.Users.User;

import javax.persistence.*;

@Entity
public class Admin extends User {

    //Constructors
    public Admin() {
    }

    public Admin(String name) {
        super(name);
    }

    //Getter & Setter


}





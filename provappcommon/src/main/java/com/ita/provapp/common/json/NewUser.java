package com.ita.provapp.common.json;

import java.io.Serializable;

public class NewUser extends User implements Serializable {

    private String password;

    public NewUser(String username, String email, String name, String surname, /*Date dateOfBirth,*/ String password) {
        super(username, name, surname, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

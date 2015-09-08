package model;

import java.io.Serializable;

/**
 * Created by jonathan on 8-9-15.
 */
public class User implements Serializable {



    private final String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }

    public boolean isUser(String username){

        return this.username.equals(username);
    }
}

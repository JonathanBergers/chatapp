package model;

import java.io.Serializable;

/**
 * Created by jonathan on 8-9-15.
 */
public class Message implements Serializable {

    private final User sender;
    private final String message;

    private String recipent;


    public Message(User sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public Message(User sender, String message, String to) {
        this.sender = sender;
        this.message = message;
        this.recipent = to;
    }

    public String getRecipent() {
        return recipent;
    }

    public User getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "Message from: "+ sender + ": " + message;
    }
}

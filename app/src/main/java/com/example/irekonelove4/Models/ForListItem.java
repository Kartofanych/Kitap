package com.example.irekonelove4.Models;

import java.util.Date;

public class ForListItem {
    private String messageText;
    private String messageUser;


    public ForListItem(String messageText, String messageUser,Integer rank) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        // Initialize to current time
    }

    public ForListItem(){

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    public String getMessageUser() { return messageUser; }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

}

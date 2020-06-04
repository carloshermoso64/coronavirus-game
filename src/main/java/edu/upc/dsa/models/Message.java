package edu.upc.dsa.models;

import org.apache.commons.lang3.RandomStringUtils;

public class Message {
    private String id;
    private String username;
    private String content;
    //private date receivedDate;


    public Message() {
    }

    public Message(String username, String content) {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.username = username;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

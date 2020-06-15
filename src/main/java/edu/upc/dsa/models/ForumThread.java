package edu.upc.dsa.models;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;

public class ForumThread implements Comparable<ForumThread>{
    private String id;
    private String author;
    private String authorId;
    private String name;
    private Date created;
    private Date lastMessage;

    public ForumThread() {
    }


    public ForumThread(String author, String authorId, String name) {
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.author = author;
        this.authorId = authorId;
        this.name = name;
        this.created = new Date();
        this.lastMessage = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Date lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public int compareTo(ForumThread o) {
        return o.getLastMessage().compareTo(lastMessage);
    }
}

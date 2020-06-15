package edu.upc.dsa.models;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;

public class ForumMessage implements Comparable<ForumMessage> {
    private String id;
    private String author;
    private String authorId;
    private String content;
    private Date posted;
    private String threadId;

    public ForumMessage() {
    }


    public ForumMessage(String author, String authorId, String content, String threadId) {
        this.author = author;
        this.authorId = authorId;
        this.content = content;
        this.posted = new Date();
        this.id = RandomStringUtils.randomAlphanumeric(8);
        this.threadId = threadId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    @Override
    public int compareTo(ForumMessage o) {
        return posted.compareTo(o.posted);
    }
}

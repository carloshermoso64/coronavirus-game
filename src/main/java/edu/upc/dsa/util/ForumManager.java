package edu.upc.dsa.util;

import edu.upc.dsa.models.ForumMessage;
import edu.upc.dsa.models.ForumThread;

import java.util.List;

public interface ForumManager {

    void createThread(String author, String name, String firstMessage);
    List<ForumThread> getAllThreads();
    List<ForumMessage> getThreadContent(String forumThreadId);
    void addMessageToThread(String author, String content, String threadId);
}

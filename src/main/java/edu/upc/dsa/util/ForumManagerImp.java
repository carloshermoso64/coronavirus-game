package edu.upc.dsa.util;

import edu.upc.dsa.DAO.*;
import edu.upc.dsa.models.ForumMessage;
import edu.upc.dsa.models.ForumThread;
import edu.upc.dsa.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ForumManagerImp implements ForumManager{

    private static ForumManagerImp instance;
    private ForumThreadDAO forumThreadDB;
    private UserDAO userDB;
    private ForumMessageDAO messageDB;

    private ForumManagerImp() {
        forumThreadDB = new ForumThreadDAOImp();
        userDB = new UserDAOImp();
        messageDB = new ForumMessageDAOImp();
    }

    public static ForumManagerImp getInstance() {
        if (instance == null)
            instance = new ForumManagerImp();
        return instance;
    }

    @Override
    public void createThread(String author, String name, String firstMessage) {
        User user = userDB.getUser("name", author);
        ForumThread thread = new ForumThread(user.getName(),user.getId(), name);
        forumThreadDB.addThread(thread);
        this.addMessageToThread(author, firstMessage, thread.getId());
    }

    @Override
    public List<ForumThread> getAllThreads() {
        ArrayList<ForumThread> threads = (ArrayList<ForumThread>) forumThreadDB.listAllThreads();
        Collections.sort(threads);
        return threads;
    }

    @Override
    public List<ForumMessage> getThreadContent(String forumThreadId) {

        ArrayList<ForumMessage> threadMessages = (ArrayList<ForumMessage>) messageDB.getAllThreadMessages(forumThreadId);
        Collections.sort(threadMessages);
        return threadMessages;
    }

    public void addMessageToThread(String author, String content, String threadId) {

        User user = userDB.getUser("name", author);
        ForumMessage forumMessage = new ForumMessage(user.getName(), user.getId(), content, threadId);
        messageDB.postMessage(forumMessage);
        ForumThread thread = forumThreadDB.getForumThread(threadId);
        thread.setLastMessage(new Date());
        forumThreadDB.updateThread(thread);
    }

}

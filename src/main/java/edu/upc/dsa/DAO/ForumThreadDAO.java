package edu.upc.dsa.DAO;

import edu.upc.dsa.models.ForumThread;

import java.util.List;

public interface ForumThreadDAO {

    void addThread(ForumThread thread);
    ForumThread getForumThread(String id);
    List<ForumThread> listAllThreads();
}

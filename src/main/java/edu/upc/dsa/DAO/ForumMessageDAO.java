package edu.upc.dsa.DAO;

import edu.upc.dsa.models.ForumMessage;
import edu.upc.dsa.models.ForumThread;

import java.util.List;

public interface ForumMessageDAO {

    void postMessage(ForumMessage message);
    List<ForumMessage> getAllThreadMessages(String threadId);

}

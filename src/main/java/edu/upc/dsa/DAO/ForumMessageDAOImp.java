package edu.upc.dsa.DAO;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import edu.upc.dsa.models.ForumMessage;
import edu.upc.dsa.models.ForumThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForumMessageDAOImp implements ForumMessageDAO{


    @Override
    public void postMessage(ForumMessage message) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.save(message);
        }

        catch (Exception e) {

        }
        finally {
            session.close();
        }
    }

    @Override
    public List<ForumMessage> getAllThreadMessages(String threadId) {
        Session session = null;

        ArrayList<ForumMessage> messages = null;
        try {
            session = FactorySession.openSession();
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("threadId", threadId);
            messages = (ArrayList<ForumMessage>) session.findAll(ForumMessage.class, params);
        }

        catch (Exception e) {

        }

        finally {
            session.close();
        }
        return messages;
    }
}

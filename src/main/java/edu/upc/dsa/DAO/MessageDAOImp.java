package edu.upc.dsa.DAO;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import edu.upc.dsa.models.Message;

import java.util.ArrayList;

public class MessageDAOImp implements MessageDAO {

    @Override
    public void AddMessage(Message message) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.save(message);
        }
        catch (Exception e) {
            int c = 1;
        }
        finally {
            session.close();
        }
    }

    @Override
    public ArrayList<Message> getAllMessages() {
        Session session = null;
        ArrayList<Message> messages = null;
        try {
            session = FactorySession.openSession();
            messages = (ArrayList<Message>) session.findAll(Message.class);
        }
        catch (Exception e) {
            int c = 1;
        }
        finally {
            session.close();
        }
        return messages;
    }
}

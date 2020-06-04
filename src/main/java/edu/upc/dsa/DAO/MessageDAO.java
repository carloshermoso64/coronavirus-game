package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Message;

import java.util.ArrayList;

public interface MessageDAO {
    void AddMessage(Message message);
    ArrayList<Message> getAllMessages();
}

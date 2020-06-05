package edu.upc.dsa.util;

import edu.upc.dsa.models.Message;

import java.util.ArrayList;

public interface MessageManager {

    void addMessage(Message msg);
    ArrayList<Message> getAllMessages();

}

package edu.upc.dsa.util;

import edu.upc.dsa.models.Message;
import edu.upc.dsa.models.ReceivedMessage;

import java.util.ArrayList;

public interface ChatManager {

    void addMessage(ReceivedMessage msg);
    ArrayList<Message> getAllMessages();
}

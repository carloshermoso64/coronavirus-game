package edu.upc.dsa.util;

import edu.upc.dsa.DAO.*;
import edu.upc.dsa.models.Message;
import edu.upc.dsa.models.ReceivedMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ChatManagerImp implements ChatManager {

    MessageDAO msgDB;
    private static ChatManagerImp instance;

    private ChatManagerImp() {
        msgDB = new MessageDAOImp();
    }

    public static ChatManagerImp getInstance() {
        if (instance==null)
            instance = new ChatManagerImp();
        return instance;
    }

    @Override
    public void addMessage(ReceivedMessage msg) {
        Message newMsg = new Message(msg.getUsername(), msg.getContent());
        newMsg.setReceivedDate(new Date());
        msgDB.AddMessage(newMsg);
    }

    @Override
    public ArrayList<Message> getAllMessages() {

        ArrayList<Message> messages = msgDB.getAllMessages();
        Collections.sort(messages);
        return messages;
    }
}



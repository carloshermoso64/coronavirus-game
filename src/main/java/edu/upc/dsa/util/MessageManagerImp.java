package edu.upc.dsa.util;

import edu.upc.dsa.DAO.*;
import edu.upc.dsa.models.Message;
import edu.upc.dsa.models.ReceivedMessage;

import java.util.ArrayList;
import java.util.Date;

public class MessageManagerImp implements MessageManager{

    MessageDAO msgDB;
    private static MessageManagerImp instance;

    private MessageManagerImp() {
        msgDB = new MessageDAOImp();
    }

    public static MessageManagerImp getInstance() {
        if (instance==null)
            instance = new MessageManagerImp();
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
        return msgDB.getAllMessages();
    }
}



package edu.upc.dsa.util;

import edu.upc.dsa.DAO.*;
import edu.upc.dsa.models.Message;

import java.util.ArrayList;

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
    public void addMessage(Message msg) {
        msgDB.AddMessage(msg);
    }

    @Override
    public ArrayList<Message> getAllMessages() {
        return msgDB.getAllMessages();
    }
}



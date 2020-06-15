package edu.upc.dsa.DAO;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import edu.upc.dsa.models.ForumThread;
import edu.upc.dsa.models.User;

import java.util.ArrayList;
import java.util.List;

public class ForumThreadDAOImp implements ForumThreadDAO{

    @Override
    public void addThread(ForumThread thread) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.save(thread);
        }

        catch (Exception e) {

        }
        finally {
            session.close();
        }
    }

    @Override
    public ForumThread getForumThread(String id) {
        Session session = null;
        ForumThread thread = null;
        try {
            session = FactorySession.openSession();
            thread = (ForumThread) session.get(ForumThread.class, "id", id);
        }

        catch (Exception e) {

        }
        finally {
            session.close();
        }
        return thread;
    }

    @Override
    public List<ForumThread> listAllThreads() {
        Session session = null;

        ArrayList<ForumThread> threads = new ArrayList<ForumThread>();
        try {
            session = FactorySession.openSession();
            threads = (ArrayList<ForumThread>) session.findAll(ForumThread.class);
        }

        catch (Exception e) {

        }

        finally {
            session.close();
        }
        return threads;
    }

    public void updateThread(ForumThread thread) {
        Session session = null;

        try {
            session = FactorySession.openSession();
            session.update(thread,thread.getId());
        }

        catch (Exception e) {

        }

        finally {
            session.close();
        }
    }
}

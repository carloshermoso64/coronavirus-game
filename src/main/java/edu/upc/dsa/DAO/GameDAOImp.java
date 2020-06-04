package edu.upc.dsa.DAO;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.User;

public class GameDAOImp implements GameDAO {
    @Override
    public Game createGame(User user) {

        Session session = null;
        Game newGame = null;
        try {
            newGame = new Game(user.getId());
            session = FactorySession.openSession();
            session.save(newGame);
        }

        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return newGame;
    }

    @Override
    public Game getGame(User user) {
        Session session = null;
        Game game = null;
        try {
            session = FactorySession.openSession();
            game = (Game) session.get(Game.class, "idUser", user.getId());
        }
        catch (Exception e) {

        }
        finally {
            session.close();
        }
        return game;
    }

    @Override
    public Game updateGame(Game game, User user) {
        Session session = null;
        Game newGame = null;
        try {
            session = FactorySession.openSession();
            session.update(game, game.getId());
            newGame = this.getGame(user);
        }
        catch (Exception e) {
            return null;
        }
        finally {
            session.close();
        }
        return newGame;
    }
}

package edu.upc.dsa.util;

import edu.upc.dsa.DAO.GameDAOImp;
import edu.upc.dsa.DAO.ShopItemDAO;
import edu.upc.dsa.DAO.ShopItemDAOImp;
import edu.upc.dsa.DAO.UserDAOImp;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.ShopItem;
import edu.upc.dsa.models.User;

import java.util.ArrayList;

public class ShopManagerImp implements ShopManager{

    private static ShopManagerImp instance;
    private UserDAOImp userDB;
    private GameDAOImp gameDB;
    private ShopItemDAO shopDB;


    private ShopManagerImp() {
        userDB = new UserDAOImp();
        gameDB = new GameDAOImp();
        shopDB = new ShopItemDAOImp();
    }

    public static ShopManagerImp getInstance() {
        if (instance==null) instance = new ShopManagerImp();
        return instance;
    }

    public boolean buyMask(String userId) {

        ShopItem mask = shopDB.getItem("Mask");
        User user = userDB.getUser("id", userId);
        Game currentGame = gameDB.getGame(user);

        if (mask.getCost() <= currentGame.getCash()) {
            int newCash = currentGame.getCash() - mask.getCost();
            currentGame.setCash(newCash);
            currentGame.setMask("TRUE");
            gameDB.updateGame(currentGame, user);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean buyNeurons(String userId) {
        ShopItem neuron = shopDB.getItem("Neuron");
        User user = userDB.getUser("id", userId);
        Game currentGame = gameDB.getGame(user);

        if (neuron.getCost() <= currentGame.getCash()) {
            int newCash = currentGame.getCash() - neuron.getCost();
            currentGame.setCash(newCash);
            currentGame.setNeurons(currentGame.getNeurons() + 1);
            gameDB.updateGame(currentGame, user);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public ShopItem getItem(String name) {
        ShopItem shopItem = shopDB.getItem(name);
        return shopItem;
    }

    @Override
    public boolean buyLife(String userId) {
        ShopItem life = shopDB.getItem("Life");
        User user = userDB.getUser("id", userId);
        Game currentGame = gameDB.getGame(user);

        if (life.getCost() <= currentGame.getCash()) {
            int newCash = currentGame.getCash() - life.getCost();
            currentGame.setCash(newCash);
            currentGame.setLifes(currentGame.getLifes() + 1);
            gameDB.updateGame(currentGame, user);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public ArrayList<ShopItem> getShop() {
        ArrayList<ShopItem> shop = shopDB.getShop();
        return shop;
    }

    @Override
    public void updateProduct(String name, int newCost) {
        shopDB.updateItem(name,newCost);
    }
}

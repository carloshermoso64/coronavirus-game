package edu.upc.dsa.DAO;

import dsa.grupo2.FactorySession;
import dsa.grupo2.Session;
import edu.upc.dsa.models.ShopItem;
import edu.upc.dsa.models.User;

import java.util.ArrayList;

public class ShopItemDAOImp implements ShopItemDAO {

    @Override
    public String addItem(String name, String description, int cost) {
        Session session = null;
        String itemId = null;
        try {
            ShopItem newItem = new ShopItem(name, description, cost);;
            session = FactorySession.openSession();
            session.save(newItem);
            itemId = newItem.getId();
        }

        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return itemId;
    }

    @Override
    public ShopItem getItem(String name) {
        Session session = null;
        ShopItem item = new ShopItem();
        try {
            session = FactorySession.openSession();
            item = (ShopItem) session.get(ShopItem.class,"name", name);
        }
        catch (Exception e) {

        }
        finally {
            session.close();
        }
        return item;
    }

    @Override
    public ShopItem updateItem(String name, int newCost) {
        Session session = null;
        ShopItem item;
        try {
            session = FactorySession.openSession();
            item = (ShopItem) session.get(ShopItem.class, "name", name);
            item.setCost(newCost);
            session.update(item, "id");
            item = (ShopItem) session.get(ShopItem.class, "name", name);
        }
        catch (Exception e) {
            item = null;
        }
        finally {
            session.close();
        }
        return item;
    }

    @Override
    public ArrayList<ShopItem> getShop() {
        Session session = null;
        ArrayList<ShopItem> shop;
        try {
            session = FactorySession.openSession();
            shop = (ArrayList<ShopItem>) session.findAll(ShopItem.class);
            return shop;
        }
        catch (Exception e) {
            shop = null;
        }
        finally {
            session.close();
        }
        return shop;
    }
}

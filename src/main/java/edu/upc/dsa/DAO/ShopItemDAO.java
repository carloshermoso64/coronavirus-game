package edu.upc.dsa.DAO;

import edu.upc.dsa.models.ShopItem;

import java.util.ArrayList;
import java.util.List;

public interface ShopItemDAO {
    public String addItem(String name, String description, int cost);
    public ShopItem getItem(String name);
    public ShopItem updateItem(String name, int newCost); //ONLY ADMIN
    public ArrayList<ShopItem> getShop();
}

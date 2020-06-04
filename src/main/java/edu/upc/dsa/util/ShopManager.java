package edu.upc.dsa.util;

import edu.upc.dsa.models.ShopItem;

import java.util.ArrayList;

public interface ShopManager {

    public ArrayList<ShopItem> getShop();
    public ShopItem getItem(String name);
    public boolean buyMask(String userId);
    public boolean buyNeurons(String userId);
    public boolean buyLife(String userId);
    public void updateProduct(String name, int newCost); //ONLY ADMIN
}

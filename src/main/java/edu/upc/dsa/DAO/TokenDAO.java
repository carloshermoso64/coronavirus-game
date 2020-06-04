package edu.upc.dsa.DAO;


public interface TokenDAO {
    public String addToken(String userId, String adminRights);
    public void delToken(String id);
    public boolean checkToken(String id);
    public boolean isAdmin(String token);
}
package edu.upc.dsa.util;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.User;

import java.util.ArrayList;

public interface JuegoManager {

    //Usuario

    public User getUsuario(String id);
    public void addUsuario (User u);
    public void addUsuario(String nombre, String correo, String contraseña);
    public void updateUsuario(User u);
    public boolean logInUsuario(String nombre, String contraseña);
    public void deleteUsuario(User u);

    //Jugador

    public void restaVida(String idjugador);

    //Mapa

    //Objetos

    public ArrayList<Objeto> getObjetos(String idpartida);


    //Partida

    public void startPartida(String idusuario, int puntuacion);
    public Partida getPartida(String idpartida);
    public ArrayList<Partida> getPartidasDeUnUsuario(String idusuario);
    public void savePartida(Partida partida);
    public void endPartida(Partida partida);


}

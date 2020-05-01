package edu.upc.dsa.util;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.Usuario;

import java.util.ArrayList;

public interface JuegoManager {

    //Usuario

    public Usuario getUsuario(String id);
    public void addUsuario (Usuario u);
    public void addUsuario(String nombre, String correo, String contraseña);
    public void updateUsuario(Usuario u);
    public boolean logInUsuario(String nombre, String contraseña);
    public void deleteUsuario(Usuario u);

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

package edu.upc.dsa.util;

import edu.upc.dsa.models.Usuario;

public interface JuegoManager {

    //Usuario

    public Usuario getUsuario(String id);
    public void addUsuario (Usuario u);
    public void addUsuario(String nombre, String correo, String contraseña);
    public void updateUsuario(Usuario u);
    public boolean logInUsuario(String nombre, String contraseña);
    public void deleteUsuario(Usuario u);

    //Jugador



    //Mapa

    //Objetos

    //Partida

    public void startPartida(String idusuarioint puntuacion);

}

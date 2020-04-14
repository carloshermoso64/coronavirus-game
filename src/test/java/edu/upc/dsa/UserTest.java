package edu.upc.dsa;

import edu.upc.dsa.models.*;
import edu.upc.dsa.util.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class UserTest {
    GameManager gm;

    @Before
    public void setUp() {
        gm = GameManagerImp.getInstance();

        this.gm.addUser("Marc","Vila","marclays");
        this.gm.addUser("Toni", "Norton", "tonilivo");
        this.gm.addUser("Ferran", "Lopez", "elbicho");
    }

    @Test //Adding users + checking the number of users
    public void testAddUsers() {

        this.gm.addUser("Juan", "Magan", "lapulga");

        Assert.assertEquals(4, this.gm.getNumUsers());
    }

    @Test
    public void testAddObjects(){

        this.gm.addObjectUser("marclays","Espada", "Espada gigante");
        this.gm.addObjectUser("marclays","Arco", "Arco magnifico");
        this.gm.addObjectUser("tonilivo", "Escudo", "Escudo gigante");

        Assert.assertEquals(2, this.gm.getnumObjectsbyUser("marclays"));
        Assert.assertEquals(1,this.gm.getnumObjectsbyUser("tonilivo"));
    }

    @Test
    public void testModifyUser(){

        this.gm.addObjectUser("elbicho", "moneda", "moneda brillante");
        this.gm.updateUser("Ferran","Lopez","fr98@yahoo.com", "elbicho");
        Assert.assertEquals("fr98@yahoo.com",this.gm.getUser("elbicho").getMail());
        Assert.assertEquals(1,this.gm.getnumObjectsbyUser("elbicho"));
    }

    @After
    public void tearDown(){
        this.gm.clear();
    }
}

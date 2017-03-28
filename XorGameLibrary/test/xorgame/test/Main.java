/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xorgame.test;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import xorgame.*;

/**
 *
 * @author Artur M. Brodzki
 */
public class Main {
    public static void main(String[] args) {
        try {
            Controler mainControler = new Controler("localhost", 3306, "xorgame", "root", "polska1024");
            mainControler.addNewUser("TestUser", "haslo");
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

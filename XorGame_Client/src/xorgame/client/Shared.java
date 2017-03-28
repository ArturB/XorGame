/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */
package xorgame.client;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Class with methods commonly used in all other classes. Intended to be statically imported whenever it is necessary.  
 * @author Artur
 */
public class Shared {
        
    public static final float BUTTON_SIZE = 0.07F;
    public static final float MATERIAL_SIZE = 0.085F;
    public static final float LOGO_SIZE = 0.08F;
    public static final float REGISTER_SIZE = 0.08F;
    public static final float LOGIN_SIZE = REGISTER_SIZE;
    public static final float ITEM_ICON_SIZE = 0.17F;
    public static final float BACKGROUND_SIZE = 0.4F;
    public static final float PLANET_PREVIEW_SIZE = 0.4F;
    public static final int   ORDERS_FONT_SIZE = 10;
    public static final int   SCROLL_SPEED = 30;
    public static final float INIT_FRAME_SIZE = 0.95F;
    public static final int   AUTO_UPDATE_PERIOD = 1000;
    
    /**
     * Returns ImageIcon object with icon saved in specifed path. Icon is resized to have height equal to given fraction of screen resolution. 
     * @param path Path where icon is saved. 
     * @param ratio Fraction specifying icon size. Icon has size equal to ratio * screen resolution. 
     * @return Image of icon loaded from specified path with given size. 
     */
    public static ImageIcon loadIcon(String path, float ratio) {
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int final_height = Math.round(height * ratio);
        ImageIcon ikona = new ImageIcon(Shared.class.getResource(path));
        Image obrazek = ikona.getImage();
        obrazek = obrazek.getScaledInstance(ikona.getIconWidth() * final_height / ikona.getIconHeight(), final_height, Image.SCALE_FAST);
        return new ImageIcon(obrazek);
    }
    
    /**
     * Returns Image object with icon saved in specifed path. Icon is resized to have height equal to given fraction of screen resolution. 
     * @param path Path where icon is saved. 
     * @param ratio Fraction specifying icon size. Icon has size equal to ratio * screen resolution. 
     * @return Image loaded from specified path with given size. 
     */
    public static Image loadImage(String path, float ratio) {
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int final_height = Math.round(height * ratio);
        ImageIcon ikona = new ImageIcon(Shared.class.getResource(path));
        Image obrazek = ikona.getImage();
       return obrazek.getScaledInstance(ikona.getIconWidth() * final_height / ikona.getIconHeight(), final_height, Image.SCALE_FAST);
    }
    
    /**
     * Returns ImageIcon object of icon loaded from specified path and resized to have specified widht. 
     * @param path Path where icon is saved. 
     * @param widht Widht of the icon. 
     * @return ImageIcon object of icon loaded from specified path and resized to have specified widht.
     */
    public static ImageIcon loadIcon(String path, int widht) {
        ImageIcon ikona = new ImageIcon(Shared.class.getResource(path));
        Image obrazek = ikona.getImage();
        int height;
        if(ikona.getIconHeight() * widht / ikona.getIconWidth() > Toolkit.getDefaultToolkit().getScreenSize().height / 2)
            height = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
        else
            height = ikona.getIconHeight() * widht / ikona.getIconWidth();
        return new ImageIcon(obrazek.getScaledInstance(widht, height, Image.SCALE_FAST));
    }
    
    /**
     * Plays a sound once, e.g. sound of keystroke. 
     * @param path Path where sound is saved. 
     */
    public static void playSound(String path) {
        Thread soundThread = new Thread(() -> {
            try {
                InputStream in = Shared.class.getResourceAsStream(path);
                AudioStream music = new AudioStream(in);
                AudioPlayer.player.start(music);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        soundThread.start();
    }
    
    /**
     * Converts string to integer. Returns 0 if string is empty. 
     * @param string String to convert. 
     * @return Integer converted from string. 
     */
    public static int parseIntSafe(String string) {
        if(string.isEmpty())
            return 0;
        else
            return Integer.valueOf(string);
    }
    
    /**
     * Returns true if given string contains only letters and digits. 
     * @param text String to check. 
     * @return True if given string contains only letters and digits, false otherwise. 
     */
    public static boolean isAlphanumeric(String text) {
        return text.matches("^[a-zA-Z0-9]*$");
    }
}

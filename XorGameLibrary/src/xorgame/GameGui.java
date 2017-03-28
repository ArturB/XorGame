/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * This class contains all images and texts used as a GUI in XorGame client. 
 * Images must be downloaded from database when client started. 
 * @author Artur M. Brodzki
 */
public class GameGui {
    private List<Gui> generalGui;
    private List<StarGui> starGui;
    private List<PlanetGui> planetGui;
    private List<MoonGui> moonGui;
    private List<Locale> locale;

    public GameGui() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        
        TypedQuery findAllGui = manager.createNamedQuery("Gui.findAll", Gui.class);
        TypedQuery findAllStarGui = manager.createNamedQuery("StarGui.findAll", StarGui.class);
        TypedQuery findAllPlanetGui = manager.createNamedQuery("PlanetGui.findAll", PlanetGui.class);
        TypedQuery findAllMoonGui = manager.createNamedQuery("MoonGui.findAll", MoonGui.class);
        TypedQuery findAllLocale = manager.createNamedQuery("Locale.findAll", Locale.class);
        
        this.generalGui = findAllGui.getResultList();
        this.starGui = findAllStarGui.getResultList();
        this.planetGui = findAllPlanetGui.getResultList();
        this.moonGui = findAllMoonGui.getResultList();
        this.locale = findAllLocale.getResultList();
    }

    public List<Gui> getGeneralGui() {
        return generalGui;
    }

    public void setGeneralGui(List<Gui> generalGui) {
        this.generalGui = generalGui;
    }

    public List<StarGui> getStarGui() {
        return starGui;
    }

    public void setStarGui(List<StarGui> starGui) {
        this.starGui = starGui;
    }

    public List<PlanetGui> getPlanetGui() {
        return planetGui;
    }

    public void setPlanetGui(List<PlanetGui> planetGui) {
        this.planetGui = planetGui;
    }

    public List<MoonGui> getMoonGui() {
        return moonGui;
    }

    public void setMoonGui(List<MoonGui> moonGui) {
        this.moonGui = moonGui;
    }

    public List<xorgame.Locale> getLocale() {
        return locale;
    }

    public void setLocale(List<xorgame.Locale> locale) {
        this.locale = locale;
    }
    
    
}

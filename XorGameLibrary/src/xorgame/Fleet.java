/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

/**
 * The class represents a fleet and contains number of different kinds of ships thatthe fleet contains.
 * @author Artur M. Brodzki
 */
public class Fleet {
    private int transporter;
    private int freighter;
    private int recycler;
    private int colonizer;
    private int spySond;
    private int fighter;
    private int ionFighter;
    private int frigate;
    private int bomber;
    private int cruiser;
    private int drednot;
    private int destroyer;
    private int deathStar;

    public Fleet(int transporter, int freighter, int recycler, int colonizer, int spySond, int fighter, int ionFighter, int frigate, int bomber, int cruiser, int drednot, int destroyer, int deathStar) {
        this.transporter = transporter;
        this.freighter = freighter;
        this.recycler = recycler;
        this.colonizer = colonizer;
        this.spySond = spySond;
        this.fighter = fighter;
        this.ionFighter = ionFighter;
        this.frigate = frigate;
        this.bomber = bomber;
        this.cruiser = cruiser;
        this.drednot = drednot;
        this.destroyer = destroyer;
        this.deathStar = deathStar;
    }

    public int getTransporter() {
        return transporter;
    }

    public void setTransporter(int transporter) {
        this.transporter = transporter;
    }

    public int getFreighter() {
        return freighter;
    }

    public void setFreighter(int freighter) {
        this.freighter = freighter;
    }

    public int getColonizer() {
        return colonizer;
    }

    public void setColonizer(int colonizer) {
        this.colonizer = colonizer;
    }

    public int getRecycler() {
        return recycler;
    }

    public void setRecycler(int recycler) {
        this.recycler = recycler;
    }

    public int getSpySond() {
        return spySond;
    }

    public void setSpySond(int spySond) {
        this.spySond = spySond;
    }

    public int getFighter() {
        return fighter;
    }

    public void setFighter(int fighter) {
        this.fighter = fighter;
    }

    public int getIonFighter() {
        return ionFighter;
    }

    public void setIonFighter(int ionFighter) {
        this.ionFighter = ionFighter;
    }

    public int getFrigate() {
        return frigate;
    }

    public void setFrigate(int frigate) {
        this.frigate = frigate;
    }

    public int getBomber() {
        return bomber;
    }

    public void setBomber(int bomber) {
        this.bomber = bomber;
    }

    public int getCruiser() {
        return cruiser;
    }

    public void setCruiser(int cruiser) {
        this.cruiser = cruiser;
    }

    public int getDrednot() {
        return drednot;
    }

    public void setDrednot(int drednot) {
        this.drednot = drednot;
    }

    public int getDestroyer() {
        return destroyer;
    }

    public void setDestroyer(int destroyer) {
        this.destroyer = destroyer;
    }

    public int getDeathStar() {
        return deathStar;
    }

    public void setDeathStar(int deathStar) {
        this.deathStar = deathStar;
    }

    
    
}

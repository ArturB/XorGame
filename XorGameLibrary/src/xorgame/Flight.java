/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class for flight table - contains information about moving fleets
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "Flight")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f"),
    @NamedQuery(name = "Flight.findById", query = "SELECT f FROM Flight f WHERE f.id = :id"),
    @NamedQuery(name = "Flight.findByStartLocation", query = "SELECT f FROM Flight f WHERE f.startLocation = :startLocation"),
    @NamedQuery(name = "Flight.findByStartTime", query = "SELECT f FROM Flight f WHERE f.startTime = :startTime"),
    @NamedQuery(name = "Flight.findByMission", query = "SELECT f FROM Flight f WHERE f.mission = :mission"),
    @NamedQuery(name = "Flight.findByFormerMission", query = "SELECT f FROM Flight f WHERE f.formerMission = :formerMission"),
    @NamedQuery(name = "Flight.findByEndLocation", query = "SELECT f FROM Flight f WHERE f.endLocation = :endLocation"),
    @NamedQuery(name = "Flight.findByEndTime", query = "SELECT f FROM Flight f WHERE f.endTime = :endTime"),
    @NamedQuery(name = "Flight.findByTransporter", query = "SELECT f FROM Flight f WHERE f.transporter = :transporter"),
    @NamedQuery(name = "Flight.findByFreighter", query = "SELECT f FROM Flight f WHERE f.freighter = :freighter"),
    @NamedQuery(name = "Flight.findByRecycler", query = "SELECT f FROM Flight f WHERE f.recycler = :recycler"),
    @NamedQuery(name = "Flight.findByColonizer", query = "SELECT f FROM Flight f WHERE f.colonizer = :colonizer"),
    @NamedQuery(name = "Flight.findBySpySond", query = "SELECT f FROM Flight f WHERE f.spySond = :spySond"),
    @NamedQuery(name = "Flight.findByFighter", query = "SELECT f FROM Flight f WHERE f.fighter = :fighter"),
    @NamedQuery(name = "Flight.findByIonFighter", query = "SELECT f FROM Flight f WHERE f.ionFighter = :ionFighter"),
    @NamedQuery(name = "Flight.findByFrigate", query = "SELECT f FROM Flight f WHERE f.frigate = :frigate"),
    @NamedQuery(name = "Flight.findByBomber", query = "SELECT f FROM Flight f WHERE f.bomber = :bomber"),
    @NamedQuery(name = "Flight.findByCruiser", query = "SELECT f FROM Flight f WHERE f.cruiser = :cruiser"),
    @NamedQuery(name = "Flight.findByDrednot", query = "SELECT f FROM Flight f WHERE f.drednot = :drednot"),
    @NamedQuery(name = "Flight.findByDestroyer", query = "SELECT f FROM Flight f WHERE f.destroyer = :destroyer"),
    @NamedQuery(name = "Flight.findByDeathStar", query = "SELECT f FROM Flight f WHERE f.deathStar = :deathStar"),
    @NamedQuery(name = "Flight.findByMetal", query = "SELECT f FROM Flight f WHERE f.metal = :metal"),
    @NamedQuery(name = "Flight.findByCristal", query = "SELECT f FROM Flight f WHERE f.cristal = :cristal"),
    @NamedQuery(name = "Flight.findByDeuter", query = "SELECT f FROM Flight f WHERE f.deuter = :deuter"),
    @NamedQuery(name = "Flight.findByAntimatter", query = "SELECT f FROM Flight f WHERE f.antimatter = :antimatter"),
    @NamedQuery(name = "Flight.findByTurnedBack", query = "SELECT f FROM Flight f WHERE f.turnedBack = :turnedBack")})
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "StartLocation")
    private String startLocation;
    @Basic(optional = false)
    @Column(name = "StartTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "Mission")
    private String mission;
    @Basic(optional = false)
    @Column(name = "FormerMission")
    private String formerMission;
    @Basic(optional = false)
    @Column(name = "EndLocation")
    private String endLocation;
    @Basic(optional = false)
    @Column(name = "EndTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "Transporter")
    private int transporter;
    @Basic(optional = false)
    @Column(name = "Freighter")
    private int freighter;
    @Basic(optional = false)
    @Column(name = "Recycler")
    private int recycler;
    @Basic(optional = false)
    @Column(name = "Colonizer")
    private int colonizer;
    @Basic(optional = false)
    @Column(name = "SpySond")
    private int spySond;
    @Basic(optional = false)
    @Column(name = "Fighter")
    private int fighter;
    @Basic(optional = false)
    @Column(name = "IonFighter")
    private int ionFighter;
    @Basic(optional = false)
    @Column(name = "Frigate")
    private int frigate;
    @Basic(optional = false)
    @Column(name = "Bomber")
    private int bomber;
    @Basic(optional = false)
    @Column(name = "Cruiser")
    private int cruiser;
    @Basic(optional = false)
    @Column(name = "Drednot")
    private int drednot;
    @Basic(optional = false)
    @Column(name = "Destroyer")
    private int destroyer;
    @Basic(optional = false)
    @Column(name = "DeathStar")
    private int deathStar;
    @Basic(optional = false)
    @Column(name = "Metal")
    private float metal;
    @Basic(optional = false)
    @Column(name = "Cristal")
    private float cristal;
    @Basic(optional = false)
    @Column(name = "Deuter")
    private float deuter;
    @Basic(optional = false)
    @Column(name = "Antimatter")
    private float antimatter;
    @Basic(optional = false)
    @Column(name = "TurnedBack")
    private String turnedBack;
    @JoinColumns({
        @JoinColumn(name = "EndX", referencedColumnName = "X"),
        @JoinColumn(name = "EndY", referencedColumnName = "Y"),
        @JoinColumn(name = "EndPosition", referencedColumnName = "Position")})
    @ManyToOne(optional = false)
    private Planet planet;
    @JoinColumns({
        @JoinColumn(name = "StartX", referencedColumnName = "X"),
        @JoinColumn(name = "StartY", referencedColumnName = "Y"),
        @JoinColumn(name = "StartPosition", referencedColumnName = "Position")})
    @ManyToOne(optional = false)
    private Planet planet1;

    public Flight() {
    }

    public Flight(Integer id) {
        this.id = id;
    }

    public Flight(Integer id, String startLocation, Date startTime, String mission, String formerMission, String endLocation, Date endTime, int transporter, int freighter, int recycler, int colonizer, int spySond, int fighter, int ionFighter, int frigate, int bomber, int cruiser, int drednot, int destroyer, int deathStar, float metal, float cristal, float deuter, float antimatter, String turnedBack) {
        this.id = id;
        this.startLocation = startLocation;
        this.startTime = startTime;
        this.mission = mission;
        this.formerMission = formerMission;
        this.endLocation = endLocation;
        this.endTime = endTime;
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
        this.metal = metal;
        this.cristal = cristal;
        this.deuter = deuter;
        this.antimatter = antimatter;
        this.turnedBack = turnedBack;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getFormerMission() {
        return formerMission;
    }

    public void setFormerMission(String formerMission) {
        this.formerMission = formerMission;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public int getRecycler() {
        return recycler;
    }

    public void setRecycler(int recycler) {
        this.recycler = recycler;
    }

    public int getColonizer() {
        return colonizer;
    }

    public void setColonizer(int colonizer) {
        this.colonizer = colonizer;
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

    public float getMetal() {
        return metal;
    }

    public void setMetal(float metal) {
        this.metal = metal;
    }

    public float getCristal() {
        return cristal;
    }

    public void setCristal(float cristal) {
        this.cristal = cristal;
    }

    public float getDeuter() {
        return deuter;
    }

    public void setDeuter(float deuter) {
        this.deuter = deuter;
    }

    public float getAntimatter() {
        return antimatter;
    }

    public void setAntimatter(float antimatter) {
        this.antimatter = antimatter;
    }

    public String getTurnedBack() {
        return turnedBack;
    }

    public void setTurnedBack(String turnedBack) {
        this.turnedBack = turnedBack;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Planet getPlanet1() {
        return planet1;
    }

    public void setPlanet1(Planet planet1) {
        this.planet1 = planet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {     
        Format formatter = new SimpleDateFormat("dd.MM 'at' hh:mm:ss");
        return "From " + getPlanet1().getPlanetPK().toString() + 
                " to " + getPlanet().getPlanetPK().toString() +
                ", " + getMission() + 
                ", end on " + formatter.format(getEndTime()); 
    }
    
}

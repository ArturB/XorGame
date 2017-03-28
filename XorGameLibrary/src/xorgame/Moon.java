/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class for moon table - represents a moon of planet in a game galaxy. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "Moon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moon.findAll", query = "SELECT m FROM Moon m"),
    @NamedQuery(name = "Moon.findByX", query = "SELECT m FROM Moon m WHERE m.moonPK.x = :x"),
    @NamedQuery(name = "Moon.findByY", query = "SELECT m FROM Moon m WHERE m.moonPK.y = :y"),
    @NamedQuery(name = "Moon.findByPosition", query = "SELECT m FROM Moon m WHERE m.moonPK.position = :position"),
    @NamedQuery(name = "Moon.findByName", query = "SELECT m FROM Moon m WHERE m.name = :name"),
    @NamedQuery(name = "Moon.findByMetal", query = "SELECT m FROM Moon m WHERE m.metal = :metal"),
    @NamedQuery(name = "Moon.findByCristal", query = "SELECT m FROM Moon m WHERE m.cristal = :cristal"),
    @NamedQuery(name = "Moon.findByDeuter", query = "SELECT m FROM Moon m WHERE m.deuter = :deuter"),
    @NamedQuery(name = "Moon.findByAntimatter", query = "SELECT m FROM Moon m WHERE m.antimatter = :antimatter"),
    @NamedQuery(name = "Moon.findByMetalMag", query = "SELECT m FROM Moon m WHERE m.metalMag = :metalMag"),
    @NamedQuery(name = "Moon.findByCristalMag", query = "SELECT m FROM Moon m WHERE m.cristalMag = :cristalMag"),
    @NamedQuery(name = "Moon.findByDeuterMag", query = "SELECT m FROM Moon m WHERE m.deuterMag = :deuterMag"),
    @NamedQuery(name = "Moon.findByAntimatterMag", query = "SELECT m FROM Moon m WHERE m.antimatterMag = :antimatterMag"),
    @NamedQuery(name = "Moon.findBySolarSats", query = "SELECT m FROM Moon m WHERE m.solarSats = :solarSats"),
    @NamedQuery(name = "Moon.findByMoonObservatory", query = "SELECT m FROM Moon m WHERE m.moonObservatory = :moonObservatory"),
    @NamedQuery(name = "Moon.findByTeleporter", query = "SELECT m FROM Moon m WHERE m.teleporter = :teleporter"),
    @NamedQuery(name = "Moon.findByShipyard", query = "SELECT m FROM Moon m WHERE m.shipyard = :shipyard"),
    @NamedQuery(name = "Moon.findByOrbitalShipyard", query = "SELECT m FROM Moon m WHERE m.orbitalShipyard = :orbitalShipyard"),
    @NamedQuery(name = "Moon.findByRobot", query = "SELECT m FROM Moon m WHERE m.robot = :robot"),
    @NamedQuery(name = "Moon.findByNanit", query = "SELECT m FROM Moon m WHERE m.nanit = :nanit"),
    @NamedQuery(name = "Moon.findByRocketLauncher", query = "SELECT m FROM Moon m WHERE m.rocketLauncher = :rocketLauncher"),
    @NamedQuery(name = "Moon.findByLaserGun", query = "SELECT m FROM Moon m WHERE m.laserGun = :laserGun"),
    @NamedQuery(name = "Moon.findByTurboLaserGun", query = "SELECT m FROM Moon m WHERE m.turboLaserGun = :turboLaserGun"),
    @NamedQuery(name = "Moon.findByIonGun", query = "SELECT m FROM Moon m WHERE m.ionGun = :ionGun"),
    @NamedQuery(name = "Moon.findByTractorGun", query = "SELECT m FROM Moon m WHERE m.tractorGun = :tractorGun"),
    @NamedQuery(name = "Moon.findByPlasmaGun", query = "SELECT m FROM Moon m WHERE m.plasmaGun = :plasmaGun"),
    @NamedQuery(name = "Moon.findByRayShield", query = "SELECT m FROM Moon m WHERE m.rayShield = :rayShield"),
    @NamedQuery(name = "Moon.findByHeavyPlasmaGun", query = "SELECT m FROM Moon m WHERE m.heavyPlasmaGun = :heavyPlasmaGun"),
    @NamedQuery(name = "Moon.findByTransporter", query = "SELECT m FROM Moon m WHERE m.transporter = :transporter"),
    @NamedQuery(name = "Moon.findByFreighter", query = "SELECT m FROM Moon m WHERE m.freighter = :freighter"),
    @NamedQuery(name = "Moon.findByRecycler", query = "SELECT m FROM Moon m WHERE m.recycler = :recycler"),
    @NamedQuery(name = "Moon.findBySpySond", query = "SELECT m FROM Moon m WHERE m.spySond = :spySond"),
    @NamedQuery(name = "Moon.findByColonizer", query = "SELECT m FROM Moon m WHERE m.colonizer = :colonizer"),
    @NamedQuery(name = "Moon.findByFighter", query = "SELECT m FROM Moon m WHERE m.fighter = :fighter"),
    @NamedQuery(name = "Moon.findByIonFighter", query = "SELECT m FROM Moon m WHERE m.ionFighter = :ionFighter"),
    @NamedQuery(name = "Moon.findByFrigate", query = "SELECT m FROM Moon m WHERE m.frigate = :frigate"),
    @NamedQuery(name = "Moon.findByBomber", query = "SELECT m FROM Moon m WHERE m.bomber = :bomber"),
    @NamedQuery(name = "Moon.findByCruiser", query = "SELECT m FROM Moon m WHERE m.cruiser = :cruiser"),
    @NamedQuery(name = "Moon.findByDrednot", query = "SELECT m FROM Moon m WHERE m.drednot = :drednot"),
    @NamedQuery(name = "Moon.findByDestroyer", query = "SELECT m FROM Moon m WHERE m.destroyer = :destroyer"),
    @NamedQuery(name = "Moon.findByDeathStar", query = "SELECT m FROM Moon m WHERE m.deathStar = :deathStar"),
    @NamedQuery(name = "Moon.findByQueueWaiting", query = "SELECT m FROM Moon m WHERE m.queueWaiting = :queueWaiting"),
    @NamedQuery(name = "Moon.findByGui", query = "SELECT m FROM Moon m WHERE m.gui = :gui"),
    @NamedQuery(name = "Moon.findByEconomyPoints", query = "SELECT m FROM Moon m WHERE m.economyPoints = :economyPoints"),
    @NamedQuery(name = "Moon.findByMilitaryPoints", query = "SELECT m FROM Moon m WHERE m.militaryPoints = :militaryPoints")})
public class Moon implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MoonPK moonPK;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Metal")
    private int metal;
    @Basic(optional = false)
    @Column(name = "Cristal")
    private int cristal;
    @Basic(optional = false)
    @Column(name = "Deuter")
    private int deuter;
    @Basic(optional = false)
    @Column(name = "Antimatter")
    private int antimatter;
    @Basic(optional = false)
    @Column(name = "MetalMag")
    private short metalMag;
    @Basic(optional = false)
    @Column(name = "CristalMag")
    private short cristalMag;
    @Basic(optional = false)
    @Column(name = "DeuterMag")
    private short deuterMag;
    @Basic(optional = false)
    @Column(name = "AntimatterMag")
    private short antimatterMag;
    @Basic(optional = false)
    @Column(name = "SolarSats")
    private int solarSats;
    @Basic(optional = false)
    @Column(name = "MoonObservatory")
    private short moonObservatory;
    @Basic(optional = false)
    @Column(name = "Teleporter")
    private short teleporter;
    @Basic(optional = false)
    @Column(name = "Shipyard")
    private short shipyard;
    @Basic(optional = false)
    @Column(name = "OrbitalShipyard")
    private short orbitalShipyard;
    @Basic(optional = false)
    @Column(name = "Robot")
    private short robot;
    @Basic(optional = false)
    @Column(name = "Nanit")
    private short nanit;
    @Basic(optional = false)
    @Column(name = "RocketLauncher")
    private int rocketLauncher;
    @Basic(optional = false)
    @Column(name = "LaserGun")
    private int laserGun;
    @Basic(optional = false)
    @Column(name = "TurboLaserGun")
    private int turboLaserGun;
    @Basic(optional = false)
    @Column(name = "IonGun")
    private int ionGun;
    @Basic(optional = false)
    @Column(name = "TractorGun")
    private int tractorGun;
    @Basic(optional = false)
    @Column(name = "PlasmaGun")
    private int plasmaGun;
    @Basic(optional = false)
    @Column(name = "RayShield")
    private short rayShield;
    @Basic(optional = false)
    @Column(name = "HeavyPlasmaGun")
    private int heavyPlasmaGun;
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
    @Column(name = "SpySond")
    private int spySond;
    @Basic(optional = false)
    @Column(name = "Colonizer")
    private int colonizer;
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
    @Column(name = "QueueWaiting")
    private boolean queueWaiting;
    @Basic(optional = false)
    @Column(name = "GUI")
    private short gui;
    @Basic(optional = false)
    @Column(name = "EconomyPoints")
    private float economyPoints;
    @Basic(optional = false)
    @Column(name = "MilitaryPoints")
    private float militaryPoints;
    @JoinColumns({
        @JoinColumn(name = "X", referencedColumnName = "X", insertable = false, updatable = false),
        @JoinColumn(name = "Y", referencedColumnName = "Y", insertable = false, updatable = false),
        @JoinColumn(name = "Position", referencedColumnName = "Position", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Planet planet;

    public Moon() {
    }

    public Moon(MoonPK moonPK) {
        this.moonPK = moonPK;
    }

    public Moon(MoonPK moonPK, String name, int metal, int cristal, int deuter, int antimatter, short metalMag, short cristalMag, short deuterMag, short antimatterMag, int solarSats, short moonObservatory, short teleporter, short shipyard, short orbitalShipyard, short robot, short nanit, int rocketLauncher, int laserGun, int turboLaserGun, int ionGun, int tractorGun, int plasmaGun, short rayShield, int heavyPlasmaGun, int transporter, int freighter, int recycler, int spySond, int colonizer, int fighter, int ionFighter, int frigate, int bomber, int cruiser, int drednot, int destroyer, int deathStar, boolean queueWaiting, short gui, float economyPoints, float militaryPoints) {
        this.moonPK = moonPK;
        this.name = name;
        this.metal = metal;
        this.cristal = cristal;
        this.deuter = deuter;
        this.antimatter = antimatter;
        this.metalMag = metalMag;
        this.cristalMag = cristalMag;
        this.deuterMag = deuterMag;
        this.antimatterMag = antimatterMag;
        this.solarSats = solarSats;
        this.moonObservatory = moonObservatory;
        this.teleporter = teleporter;
        this.shipyard = shipyard;
        this.orbitalShipyard = orbitalShipyard;
        this.robot = robot;
        this.nanit = nanit;
        this.rocketLauncher = rocketLauncher;
        this.laserGun = laserGun;
        this.turboLaserGun = turboLaserGun;
        this.ionGun = ionGun;
        this.tractorGun = tractorGun;
        this.plasmaGun = plasmaGun;
        this.rayShield = rayShield;
        this.heavyPlasmaGun = heavyPlasmaGun;
        this.transporter = transporter;
        this.freighter = freighter;
        this.recycler = recycler;
        this.spySond = spySond;
        this.colonizer = colonizer;
        this.fighter = fighter;
        this.ionFighter = ionFighter;
        this.frigate = frigate;
        this.bomber = bomber;
        this.cruiser = cruiser;
        this.drednot = drednot;
        this.destroyer = destroyer;
        this.deathStar = deathStar;
        this.queueWaiting = queueWaiting;
        this.gui = gui;
        this.economyPoints = economyPoints;
        this.militaryPoints = militaryPoints;
    }

    public Moon(short x, short y, short position) {
        this.moonPK = new MoonPK(x, y, position);
    }

    public MoonPK getMoonPK() {
        return moonPK;
    }

    public void setMoonPK(MoonPK moonPK) {
        this.moonPK = moonPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMetal() {
        return metal;
    }

    public void setMetal(int metal) {
        this.metal = metal;
    }

    public int getCristal() {
        return cristal;
    }

    public void setCristal(int cristal) {
        this.cristal = cristal;
    }

    public int getDeuter() {
        return deuter;
    }

    public void setDeuter(int deuter) {
        this.deuter = deuter;
    }

    public int getAntimatter() {
        return antimatter;
    }

    public void setAntimatter(int antimatter) {
        this.antimatter = antimatter;
    }

    public short getMetalMag() {
        return metalMag;
    }

    public void setMetalMag(short metalMag) {
        this.metalMag = metalMag;
    }

    public short getCristalMag() {
        return cristalMag;
    }

    public void setCristalMag(short cristalMag) {
        this.cristalMag = cristalMag;
    }

    public short getDeuterMag() {
        return deuterMag;
    }

    public void setDeuterMag(short deuterMag) {
        this.deuterMag = deuterMag;
    }

    public short getAntimatterMag() {
        return antimatterMag;
    }

    public void setAntimatterMag(short antimatterMag) {
        this.antimatterMag = antimatterMag;
    }

    public int getSolarSats() {
        return solarSats;
    }

    public void setSolarSats(int solarSats) {
        this.solarSats = solarSats;
    }

    public short getMoonObservatory() {
        return moonObservatory;
    }

    public void setMoonObservatory(short moonObservatory) {
        this.moonObservatory = moonObservatory;
    }

    public short getTeleporter() {
        return teleporter;
    }

    public void setTeleporter(short teleporter) {
        this.teleporter = teleporter;
    }

    public short getShipyard() {
        return shipyard;
    }

    public void setShipyard(short shipyard) {
        this.shipyard = shipyard;
    }

    public short getOrbitalShipyard() {
        return orbitalShipyard;
    }

    public void setOrbitalShipyard(short orbitalShipyard) {
        this.orbitalShipyard = orbitalShipyard;
    }

    public short getRobot() {
        return robot;
    }

    public void setRobot(short robot) {
        this.robot = robot;
    }

    public short getNanit() {
        return nanit;
    }

    public void setNanit(short nanit) {
        this.nanit = nanit;
    }

    public int getRocketLauncher() {
        return rocketLauncher;
    }

    public void setRocketLauncher(int rocketLauncher) {
        this.rocketLauncher = rocketLauncher;
    }

    public int getLaserGun() {
        return laserGun;
    }

    public void setLaserGun(int laserGun) {
        this.laserGun = laserGun;
    }

    public int getTurboLaserGun() {
        return turboLaserGun;
    }

    public void setTurboLaserGun(int turboLaserGun) {
        this.turboLaserGun = turboLaserGun;
    }

    public int getIonGun() {
        return ionGun;
    }

    public void setIonGun(int ionGun) {
        this.ionGun = ionGun;
    }

    public int getTractorGun() {
        return tractorGun;
    }

    public void setTractorGun(int tractorGun) {
        this.tractorGun = tractorGun;
    }

    public int getPlasmaGun() {
        return plasmaGun;
    }

    public void setPlasmaGun(int plasmaGun) {
        this.plasmaGun = plasmaGun;
    }

    public short getRayShield() {
        return rayShield;
    }

    public void setRayShield(short rayShield) {
        this.rayShield = rayShield;
    }

    public int getHeavyPlasmaGun() {
        return heavyPlasmaGun;
    }

    public void setHeavyPlasmaGun(int heavyPlasmaGun) {
        this.heavyPlasmaGun = heavyPlasmaGun;
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

    public int getSpySond() {
        return spySond;
    }

    public void setSpySond(int spySond) {
        this.spySond = spySond;
    }

    public int getColonizer() {
        return colonizer;
    }

    public void setColonizer(int colonizer) {
        this.colonizer = colonizer;
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

    public boolean getQueueWaiting() {
        return queueWaiting;
    }

    public void setQueueWaiting(boolean queueWaiting) {
        this.queueWaiting = queueWaiting;
    }

    public short getGui() {
        return gui;
    }

    public void setGui(short gui) {
        this.gui = gui;
    }

    public float getEconomyPoints() {
        return economyPoints;
    }

    public void setEconomyPoints(float economyPoints) {
        this.economyPoints = economyPoints;
    }

    public float getMilitaryPoints() {
        return militaryPoints;
    }

    public void setMilitaryPoints(float militaryPoints) {
        this.militaryPoints = militaryPoints;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moonPK != null ? moonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moon)) {
            return false;
        }
        Moon other = (Moon) object;
        if ((this.moonPK == null && other.moonPK != null) || (this.moonPK != null && !this.moonPK.equals(other.moonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName() + getMoonPK();
    }
    
}

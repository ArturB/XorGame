/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity class for planet table - represents planet in the game galaxy. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "Planet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planet.findAll", query = "SELECT p FROM Planet p"),
    @NamedQuery(name = "Planet.findByX", query = "SELECT p FROM Planet p WHERE p.planetPK.x = :x"),
    @NamedQuery(name = "Planet.findByY", query = "SELECT p FROM Planet p WHERE p.planetPK.y = :y"),
    @NamedQuery(name = "Planet.findByPosition", query = "SELECT p FROM Planet p WHERE p.planetPK.position = :position"),
    @NamedQuery(name = "Planet.findByName", query = "SELECT p FROM Planet p WHERE p.name = :name"),
    @NamedQuery(name = "Planet.findByMetal", query = "SELECT p FROM Planet p WHERE p.metal = :metal"),
    @NamedQuery(name = "Planet.findByCristal", query = "SELECT p FROM Planet p WHERE p.cristal = :cristal"),
    @NamedQuery(name = "Planet.findByDeuter", query = "SELECT p FROM Planet p WHERE p.deuter = :deuter"),
    @NamedQuery(name = "Planet.findByAntimatter", query = "SELECT p FROM Planet p WHERE p.antimatter = :antimatter"),
    @NamedQuery(name = "Planet.findByUpdateTime", query = "SELECT p FROM Planet p WHERE p.updateTime = :updateTime"),
    @NamedQuery(name = "Planet.findByMetalDebris", query = "SELECT p FROM Planet p WHERE p.metalDebris = :metalDebris"),
    @NamedQuery(name = "Planet.findByCristalDebris", query = "SELECT p FROM Planet p WHERE p.cristalDebris = :cristalDebris"),
    @NamedQuery(name = "Planet.findByMetalMine", query = "SELECT p FROM Planet p WHERE p.metalMine = :metalMine"),
    @NamedQuery(name = "Planet.findByCristalMine", query = "SELECT p FROM Planet p WHERE p.cristalMine = :cristalMine"),
    @NamedQuery(name = "Planet.findByDeuterExt", query = "SELECT p FROM Planet p WHERE p.deuterExt = :deuterExt"),
    @NamedQuery(name = "Planet.findByMetalMag", query = "SELECT p FROM Planet p WHERE p.metalMag = :metalMag"),
    @NamedQuery(name = "Planet.findByCristalMag", query = "SELECT p FROM Planet p WHERE p.cristalMag = :cristalMag"),
    @NamedQuery(name = "Planet.findByDeuterMag", query = "SELECT p FROM Planet p WHERE p.deuterMag = :deuterMag"),
    @NamedQuery(name = "Planet.findByAntimatterMag", query = "SELECT p FROM Planet p WHERE p.antimatterMag = :antimatterMag"),
    @NamedQuery(name = "Planet.findByFusion", query = "SELECT p FROM Planet p WHERE p.fusion = :fusion"),
    @NamedQuery(name = "Planet.findBySolarSats", query = "SELECT p FROM Planet p WHERE p.solarSats = :solarSats"),
    @NamedQuery(name = "Planet.findByAccelerator", query = "SELECT p FROM Planet p WHERE p.accelerator = :accelerator"),
    @NamedQuery(name = "Planet.findByAcceleratorMode", query = "SELECT p FROM Planet p WHERE p.acceleratorMode = :acceleratorMode"),
    @NamedQuery(name = "Planet.findByRobot", query = "SELECT p FROM Planet p WHERE p.robot = :robot"),
    @NamedQuery(name = "Planet.findByNanit", query = "SELECT p FROM Planet p WHERE p.nanit = :nanit"),
    @NamedQuery(name = "Planet.findByShipyard", query = "SELECT p FROM Planet p WHERE p.shipyard = :shipyard"),
    @NamedQuery(name = "Planet.findByObservatory", query = "SELECT p FROM Planet p WHERE p.observatory = :observatory"),
    @NamedQuery(name = "Planet.findByLaboratory", query = "SELECT p FROM Planet p WHERE p.laboratory = :laboratory"),
    @NamedQuery(name = "Planet.findByResearchNet", query = "SELECT p FROM Planet p WHERE p.researchNet = :researchNet"),
    @NamedQuery(name = "Planet.findByRocketLauncher", query = "SELECT p FROM Planet p WHERE p.rocketLauncher = :rocketLauncher"),
    @NamedQuery(name = "Planet.findByLaserGun", query = "SELECT p FROM Planet p WHERE p.laserGun = :laserGun"),
    @NamedQuery(name = "Planet.findByTurboLaserGun", query = "SELECT p FROM Planet p WHERE p.turboLaserGun = :turboLaserGun"),
    @NamedQuery(name = "Planet.findByIonGun", query = "SELECT p FROM Planet p WHERE p.ionGun = :ionGun"),
    @NamedQuery(name = "Planet.findByTractorGun", query = "SELECT p FROM Planet p WHERE p.tractorGun = :tractorGun"),
    @NamedQuery(name = "Planet.findByPlasmaGun", query = "SELECT p FROM Planet p WHERE p.plasmaGun = :plasmaGun"),
    @NamedQuery(name = "Planet.findByRayShield", query = "SELECT p FROM Planet p WHERE p.rayShield = :rayShield"),
    @NamedQuery(name = "Planet.findByHeavyPlasmaGun", query = "SELECT p FROM Planet p WHERE p.heavyPlasmaGun = :heavyPlasmaGun"),
    @NamedQuery(name = "Planet.findByTransporter", query = "SELECT p FROM Planet p WHERE p.transporter = :transporter"),
    @NamedQuery(name = "Planet.findByFreighter", query = "SELECT p FROM Planet p WHERE p.freighter = :freighter"),
    @NamedQuery(name = "Planet.findByRecycler", query = "SELECT p FROM Planet p WHERE p.recycler = :recycler"),
    @NamedQuery(name = "Planet.findBySpySond", query = "SELECT p FROM Planet p WHERE p.spySond = :spySond"),
    @NamedQuery(name = "Planet.findByColonizer", query = "SELECT p FROM Planet p WHERE p.colonizer = :colonizer"),
    @NamedQuery(name = "Planet.findByFighter", query = "SELECT p FROM Planet p WHERE p.fighter = :fighter"),
    @NamedQuery(name = "Planet.findByIonFighter", query = "SELECT p FROM Planet p WHERE p.ionFighter = :ionFighter"),
    @NamedQuery(name = "Planet.findByFrigate", query = "SELECT p FROM Planet p WHERE p.frigate = :frigate"),
    @NamedQuery(name = "Planet.findByBomber", query = "SELECT p FROM Planet p WHERE p.bomber = :bomber"),
    @NamedQuery(name = "Planet.findByCruiser", query = "SELECT p FROM Planet p WHERE p.cruiser = :cruiser"),
    @NamedQuery(name = "Planet.findByDrednot", query = "SELECT p FROM Planet p WHERE p.drednot = :drednot"),
    @NamedQuery(name = "Planet.findByDestroyer", query = "SELECT p FROM Planet p WHERE p.destroyer = :destroyer"),
    @NamedQuery(name = "Planet.findByDeathStar", query = "SELECT p FROM Planet p WHERE p.deathStar = :deathStar"),
    @NamedQuery(name = "Planet.findByQueueWaiting", query = "SELECT p FROM Planet p WHERE p.queueWaiting = :queueWaiting"),
    @NamedQuery(name = "Planet.findByGui", query = "SELECT p FROM Planet p WHERE p.gui = :gui"),
    @NamedQuery(name = "Planet.findByEconomyPoints", query = "SELECT p FROM Planet p WHERE p.economyPoints = :economyPoints"),
    @NamedQuery(name = "Planet.findByMilitaryPoints", query = "SELECT p FROM Planet p WHERE p.militaryPoints = :militaryPoints")})
public class Planet implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlanetPK planetPK;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
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
    @Column(name = "UpdateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Basic(optional = false)
    @Column(name = "MetalDebris")
    private float metalDebris;
    @Basic(optional = false)
    @Column(name = "CristalDebris")
    private float cristalDebris;
    @Basic(optional = false)
    @Column(name = "MetalMine")
    private short metalMine;
    @Basic(optional = false)
    @Column(name = "CristalMine")
    private short cristalMine;
    @Basic(optional = false)
    @Column(name = "DeuterExt")
    private short deuterExt;
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
    @Column(name = "Fusion")
    private short fusion;
    @Basic(optional = false)
    @Column(name = "SolarSats")
    private int solarSats;
    @Basic(optional = false)
    @Column(name = "Accelerator")
    private short accelerator;
    @Basic(optional = false)
    @Column(name = "AcceleratorMode")
    private String acceleratorMode;
    @Basic(optional = false)
    @Column(name = "Robot")
    private short robot;
    @Basic(optional = false)
    @Column(name = "Nanit")
    private short nanit;
    @Basic(optional = false)
    @Column(name = "Shipyard")
    private short shipyard;
    @Basic(optional = false)
    @Column(name = "Observatory")
    private short observatory;
    @Basic(optional = false)
    @Column(name = "Laboratory")
    private short laboratory;
    @Basic(optional = false)
    @Column(name = "ResearchNet")
    private boolean researchNet;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planet")
    private Set<Flight> flightSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planet1")
    private Set<Flight> flightSet1;
    @JoinColumns({
        @JoinColumn(name = "X", referencedColumnName = "X", insertable = false, updatable = false),
        @JoinColumn(name = "Y", referencedColumnName = "Y", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Star star;
    @JoinColumn(name = "User", referencedColumnName = "Nick")
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planet")
    private Set<ShipyardOrder> shipyardOrderSet;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "planet")
    private Moon moon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planet")
    private Set<BuildingOrder> buildingOrderSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planet")
    private Set<ResearchOrder> researchOrderSet;

    public Planet() {
    }

    public Planet(PlanetPK planetPK) {
        this.planetPK = planetPK;
    }

    public Planet(PlanetPK planetPK, String name, float metal, float cristal, float deuter, float antimatter, Date updateTime, float metalDebris, float cristalDebris, short metalMine, short cristalMine, short deuterExt, short metalMag, short cristalMag, short deuterMag, short antimatterMag, short fusion, int solarSats, short accelerator, String acceleratorMode, short robot, short nanit, short shipyard, short observatory, short laboratory, boolean researchNet, int rocketLauncher, int laserGun, int turboLaserGun, int ionGun, int tractorGun, int plasmaGun, short rayShield, int heavyPlasmaGun, int transporter, int freighter, int recycler, int spySond, int colonizer, int fighter, int ionFighter, int frigate, int bomber, int cruiser, int drednot, int destroyer, int deathStar, boolean queueWaiting, short gui, float economyPoints, float militaryPoints) {
        this.planetPK = planetPK;
        this.name = name;
        this.metal = metal;
        this.cristal = cristal;
        this.deuter = deuter;
        this.antimatter = antimatter;
        this.updateTime = updateTime;
        this.metalDebris = metalDebris;
        this.cristalDebris = cristalDebris;
        this.metalMine = metalMine;
        this.cristalMine = cristalMine;
        this.deuterExt = deuterExt;
        this.metalMag = metalMag;
        this.cristalMag = cristalMag;
        this.deuterMag = deuterMag;
        this.antimatterMag = antimatterMag;
        this.fusion = fusion;
        this.solarSats = solarSats;
        this.accelerator = accelerator;
        this.acceleratorMode = acceleratorMode;
        this.robot = robot;
        this.nanit = nanit;
        this.shipyard = shipyard;
        this.observatory = observatory;
        this.laboratory = laboratory;
        this.researchNet = researchNet;
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

    public Planet(short x, short y, short position) {
        this.planetPK = new PlanetPK(x, y, position);
    }

    public PlanetPK getPlanetPK() {
        return planetPK;
    }

    public void setPlanetPK(PlanetPK planetPK) {
        this.planetPK = planetPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public float getMetalDebris() {
        return metalDebris;
    }

    public void setMetalDebris(float metalDebris) {
        this.metalDebris = metalDebris;
    }

    public float getCristalDebris() {
        return cristalDebris;
    }

    public void setCristalDebris(float cristalDebris) {
        this.cristalDebris = cristalDebris;
    }

    public short getMetalMine() {
        return metalMine;
    }

    public void setMetalMine(short metalMine) {
        this.metalMine = metalMine;
    }

    public short getCristalMine() {
        return cristalMine;
    }

    public void setCristalMine(short cristalMine) {
        this.cristalMine = cristalMine;
    }

    public short getDeuterExt() {
        return deuterExt;
    }

    public void setDeuterExt(short deuterExt) {
        this.deuterExt = deuterExt;
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

    public short getFusion() {
        return fusion;
    }

    public void setFusion(short fusion) {
        this.fusion = fusion;
    }

    public int getSolarSats() {
        return solarSats;
    }

    public void setSolarSats(int solarSats) {
        this.solarSats = solarSats;
    }

    public short getAccelerator() {
        return accelerator;
    }

    public void setAccelerator(short accelerator) {
        this.accelerator = accelerator;
    }

    public String getAcceleratorMode() {
        return acceleratorMode;
    }

    public void setAcceleratorMode(String acceleratorMode) {
        this.acceleratorMode = acceleratorMode;
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

    public short getShipyard() {
        return shipyard;
    }

    public void setShipyard(short shipyard) {
        this.shipyard = shipyard;
    }

    public short getObservatory() {
        return observatory;
    }

    public void setObservatory(short observatory) {
        this.observatory = observatory;
    }

    public short getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(short laboratory) {
        this.laboratory = laboratory;
    }

    public boolean getResearchNet() {
        return researchNet;
    }

    public void setResearchNet(boolean researchNet) {
        this.researchNet = researchNet;
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

    @XmlTransient
    public Set<Flight> getFlightSet() {
        return flightSet;
    }

    public void setFlightSet(Set<Flight> flightSet) {
        this.flightSet = flightSet;
    }

    @XmlTransient
    public Set<Flight> getFlightSet1() {
        return flightSet1;
    }

    public void setFlightSet1(Set<Flight> flightSet1) {
        this.flightSet1 = flightSet1;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public Set<ShipyardOrder> getShipyardOrderSet() {
        return shipyardOrderSet;
    }

    public void setShipyardOrderSet(Set<ShipyardOrder> shipyardOrderSet) {
        this.shipyardOrderSet = shipyardOrderSet;
    }

    public Moon getMoon() {
        return moon;
    }

    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    @XmlTransient
    public Set<BuildingOrder> getBuildingOrderSet() {
        return buildingOrderSet;
    }

    public void setBuildingOrderSet(Set<BuildingOrder> buildingOrderSet) {
        this.buildingOrderSet = buildingOrderSet;
    }

    @XmlTransient
    public Set<ResearchOrder> getResearchOrderSet() {
        return researchOrderSet;
    }

    public void setResearchOrderSet(Set<ResearchOrder> researchOrderSet) {
        this.researchOrderSet = researchOrderSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planetPK != null ? planetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planet)) {
            return false;
        }
        Planet other = (Planet) object;
        if ((this.planetPK == null && other.planetPK != null) || (this.planetPK != null && !this.planetPK.equals(other.planetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName() + " " + getPlanetPK();
    }
    
}

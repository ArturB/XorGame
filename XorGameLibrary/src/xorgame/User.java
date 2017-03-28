/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity class for user table - containing all essential information about user of the game. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByNick", query = "SELECT u FROM User u WHERE u.nick = :nick"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByResearchPoints", query = "SELECT u FROM User u WHERE u.researchPoints = :researchPoints"),
    @NamedQuery(name = "User.findByEnergetic", query = "SELECT u FROM User u WHERE u.energetic = :energetic"),
    @NamedQuery(name = "User.findByNanotechnology", query = "SELECT u FROM User u WHERE u.nanotechnology = :nanotechnology"),
    @NamedQuery(name = "User.findByAstrophysics", query = "SELECT u FROM User u WHERE u.astrophysics = :astrophysics"),
    @NamedQuery(name = "User.findByHyperspace", query = "SELECT u FROM User u WHERE u.hyperspace = :hyperspace"),
    @NamedQuery(name = "User.findByAstronavigation", query = "SELECT u FROM User u WHERE u.astronavigation = :astronavigation"),
    @NamedQuery(name = "User.findByLaser", query = "SELECT u FROM User u WHERE u.laser = :laser"),
    @NamedQuery(name = "User.findByIon", query = "SELECT u FROM User u WHERE u.ion = :ion"),
    @NamedQuery(name = "User.findByPlasma", query = "SELECT u FROM User u WHERE u.plasma = :plasma"),
    @NamedQuery(name = "User.findByGravitons", query = "SELECT u FROM User u WHERE u.gravitons = :gravitons"),
    @NamedQuery(name = "User.findByCombat", query = "SELECT u FROM User u WHERE u.combat = :combat"),
    @NamedQuery(name = "User.findByShield", query = "SELECT u FROM User u WHERE u.shield = :shield"),
    @NamedQuery(name = "User.findByMaterial", query = "SELECT u FROM User u WHERE u.material = :material"),
    @NamedQuery(name = "User.findByIonEngine", query = "SELECT u FROM User u WHERE u.ionEngine = :ionEngine"),
    @NamedQuery(name = "User.findByHyperEngine", query = "SELECT u FROM User u WHERE u.hyperEngine = :hyperEngine"),
    @NamedQuery(name = "User.findByRetreatThreshold", query = "SELECT u FROM User u WHERE u.retreatThreshold = :retreatThreshold"),
    @NamedQuery(name = "User.findByPlanetSorting", query = "SELECT u FROM User u WHERE u.planetSorting = :planetSorting"),
    @NamedQuery(name = "User.findByUrlop", query = "SELECT u FROM User u WHERE u.urlop = :urlop")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Nick")
    private String nick;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Column(name = "ResearchPoints")
    private float researchPoints;
    @Basic(optional = false)
    @Column(name = "Energetic")
    private short energetic;
    @Basic(optional = false)
    @Column(name = "Nanotechnology")
    private short nanotechnology;
    @Basic(optional = false)
    @Column(name = "Astrophysics")
    private short astrophysics;
    @Basic(optional = false)
    @Column(name = "Hyperspace")
    private short hyperspace;
    @Basic(optional = false)
    @Column(name = "Astronavigation")
    private short astronavigation;
    @Basic(optional = false)
    @Column(name = "Laser")
    private short laser;
    @Basic(optional = false)
    @Column(name = "Ion")
    private short ion;
    @Basic(optional = false)
    @Column(name = "Plasma")
    private short plasma;
    @Basic(optional = false)
    @Column(name = "Gravitons")
    private short gravitons;
    @Basic(optional = false)
    @Column(name = "Combat")
    private short combat;
    @Basic(optional = false)
    @Column(name = "Shield")
    private short shield;
    @Basic(optional = false)
    @Column(name = "Material")
    private short material;
    @Basic(optional = false)
    @Column(name = "IonEngine")
    private short ionEngine;
    @Basic(optional = false)
    @Column(name = "HyperEngine")
    private short hyperEngine;
    @Basic(optional = false)
    @Column(name = "RetreatThreshold")
    private float retreatThreshold;
    @Basic(optional = false)
    @Column(name = "PlanetSorting")
    private String planetSorting;
    @Basic(optional = false)
    @Column(name = "Urlop")
    private boolean urlop;
    @OneToMany(mappedBy = "user")
    private Set<Planet> planetSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userFrom")
    private Set<Message> messageSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTo")
    private Set<Message> messageSet1;

    public User() {
    }

    public User(String nick) {
        this.nick = nick;
    }

    public User(String nick, String password, float researchPoints, short energetic, short nanotechnology, short astrophysics, short hyperspace, short astronavigation, short laser, short ion, short plasma, short gravitons, short combat, short shield, short material, short ionEngine, short hyperEngine, float retreatThreshold, String planetSorting, boolean urlop) {
        this.nick = nick;
        this.password = password;
        this.researchPoints = researchPoints;
        this.energetic = energetic;
        this.nanotechnology = nanotechnology;
        this.astrophysics = astrophysics;
        this.hyperspace = hyperspace;
        this.astronavigation = astronavigation;
        this.laser = laser;
        this.ion = ion;
        this.plasma = plasma;
        this.gravitons = gravitons;
        this.combat = combat;
        this.shield = shield;
        this.material = material;
        this.ionEngine = ionEngine;
        this.hyperEngine = hyperEngine;
        this.retreatThreshold = retreatThreshold;
        this.planetSorting = planetSorting;
        this.urlop = urlop;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getResearchPoints() {
        return researchPoints;
    }

    public void setResearchPoints(float researchPoints) {
        this.researchPoints = researchPoints;
    }

    public short getEnergetic() {
        return energetic;
    }

    public void setEnergetic(short energetic) {
        this.energetic = energetic;
    }

    public short getNanotechnology() {
        return nanotechnology;
    }

    public void setNanotechnology(short nanotechnology) {
        this.nanotechnology = nanotechnology;
    }

    public short getAstrophysics() {
        return astrophysics;
    }

    public void setAstrophysics(short astrophysics) {
        this.astrophysics = astrophysics;
    }

    public short getHyperspace() {
        return hyperspace;
    }

    public void setHyperspace(short hyperspace) {
        this.hyperspace = hyperspace;
    }

    public short getAstronavigation() {
        return astronavigation;
    }

    public void setAstronavigation(short astronavigation) {
        this.astronavigation = astronavigation;
    }

    public short getLaser() {
        return laser;
    }

    public void setLaser(short laser) {
        this.laser = laser;
    }

    public short getIon() {
        return ion;
    }

    public void setIon(short ion) {
        this.ion = ion;
    }

    public short getPlasma() {
        return plasma;
    }

    public void setPlasma(short plasma) {
        this.plasma = plasma;
    }

    public short getGravitons() {
        return gravitons;
    }

    public void setGravitons(short gravitons) {
        this.gravitons = gravitons;
    }

    public short getCombat() {
        return combat;
    }

    public void setCombat(short combat) {
        this.combat = combat;
    }

    public short getShield() {
        return shield;
    }

    public void setShield(short shield) {
        this.shield = shield;
    }

    public short getMaterial() {
        return material;
    }

    public void setMaterial(short material) {
        this.material = material;
    }

    public short getIonEngine() {
        return ionEngine;
    }

    public void setIonEngine(short ionEngine) {
        this.ionEngine = ionEngine;
    }

    public short getHyperEngine() {
        return hyperEngine;
    }

    public void setHyperEngine(short hyperEngine) {
        this.hyperEngine = hyperEngine;
    }

    public float getRetreatThreshold() {
        return retreatThreshold;
    }

    public void setRetreatThreshold(float retreatThreshold) {
        this.retreatThreshold = retreatThreshold;
    }

    public String getPlanetSorting() {
        return planetSorting;
    }

    public void setPlanetSorting(String planetSorting) {
        this.planetSorting = planetSorting;
    }

    public boolean getUrlop() {
        return urlop;
    }

    public void setUrlop(boolean urlop) {
        this.urlop = urlop;
    }

    @XmlTransient
    public Set<Planet> getPlanetSet() {
        return planetSet;
    }

    public void setPlanetSet(Set<Planet> planetSet) {
        this.planetSet = planetSet;
    }

    @XmlTransient
    public Set<Message> getMessageSet() {
        return messageSet;
    }

    public void setMessageSet(Set<Message> messageSet) {
        this.messageSet = messageSet;
    }

    @XmlTransient
    public Set<Message> getMessageSet1() {
        return messageSet1;
    }

    public void setMessageSet1(Set<Message> messageSet1) {
        this.messageSet1 = messageSet1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nick != null ? nick.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.nick == null && other.nick != null) || (this.nick != null && !this.nick.equals(other.nick))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xorgame.User[ nick=" + nick + " ]";
    }
    
}

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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity class for star table - representing a star in game galaxy. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "Star")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Star.findAll", query = "SELECT s FROM Star s"),
    @NamedQuery(name = "Star.findByX", query = "SELECT s FROM Star s WHERE s.starPK.x = :x"),
    @NamedQuery(name = "Star.findByY", query = "SELECT s FROM Star s WHERE s.starPK.y = :y"),
    @NamedQuery(name = "Star.findByRadius", query = "SELECT s FROM Star s WHERE s.radius = :radius"),
    @NamedQuery(name = "Star.findByGui", query = "SELECT s FROM Star s WHERE s.gui = :gui")})
public class Star implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StarPK starPK;
    @Basic(optional = false)
    @Column(name = "Radius")
    private int radius;
    @Basic(optional = false)
    @Column(name = "GUI")
    private short gui;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "star")
    private Set<Planet> planetSet;

    public Star() {
    }

    public Star(StarPK starPK) {
        this.starPK = starPK;
    }

    public Star(StarPK starPK, int radius, short gui) {
        this.starPK = starPK;
        this.radius = radius;
        this.gui = gui;
    }

    public Star(short x, short y) {
        this.starPK = new StarPK(x, y);
    }

    public StarPK getStarPK() {
        return starPK;
    }

    public void setStarPK(StarPK starPK) {
        this.starPK = starPK;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public short getGui() {
        return gui;
    }

    public void setGui(short gui) {
        this.gui = gui;
    }

    @XmlTransient
    public Set<Planet> getPlanetSet() {
        return planetSet;
    }

    public void setPlanetSet(Set<Planet> planetSet) {
        this.planetSet = planetSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (starPK != null ? starPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Star)) {
            return false;
        }
        Star other = (Star) object;
        if ((this.starPK == null && other.starPK != null) || (this.starPK != null && !this.starPK.equals(other.starPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xorgame.Star[ starPK=" + starPK + " ]";
    }
    
}

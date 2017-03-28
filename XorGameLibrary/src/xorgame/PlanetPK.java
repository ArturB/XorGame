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
import javax.persistence.Embeddable;

/**
 * Entity class for primary key of planet table.
 * Contains coordinates of the solar systems to which the planet belongs and positon of the planet in the solar system. 
 * @author Artur M. Brodzki
 */
@Embeddable
public class PlanetPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "X")
    private short x;
    @Basic(optional = false)
    @Column(name = "Y")
    private short y;
    @Basic(optional = false)
    @Column(name = "Position")
    private short position;

    public PlanetPK() {
    }

    public PlanetPK(short x, short y, short position) {
        this.x = x;
        this.y = y;
        this.position = position;
    }

    public short getX() {
        return x;
    }

    public void setX(short x) {
        this.x = x;
    }

    public short getY() {
        return y;
    }

    public void setY(short y) {
        this.y = y;
    }

    public short getPosition() {
        return position;
    }

    public void setPosition(short position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) x;
        hash += (int) y;
        hash += (int) position;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanetPK)) {
            return false;
        }
        PlanetPK other = (PlanetPK) object;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.position != other.position) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Integer.toString(getX()) + ":" + Integer.toString(getY()) + ":" + Integer.toString(getPosition());
    }
    
}

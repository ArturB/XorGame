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
 * Entity class for primary key of star table - containing its (X,Y) cartesian coordinates in the game galaxy. 
 * @author Artur M. Brodzki
 */
@Embeddable
public class StarPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "X")
    private short x;
    @Basic(optional = false)
    @Column(name = "Y")
    private short y;

    public StarPK() {
    }

    public StarPK(short x, short y) {
        this.x = x;
        this.y = y;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) x;
        hash += (int) y;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StarPK)) {
            return false;
        }
        StarPK other = (StarPK) object;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xorgame.StarPK[ x=" + x + ", y=" + y + " ]";
    }
    
}

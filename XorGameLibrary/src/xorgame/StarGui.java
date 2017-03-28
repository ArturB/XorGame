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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class for star_gui table - containing possible images of stars to display in game client. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "Star_Gui")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StarGui.findAll", query = "SELECT s FROM StarGui s"),
    @NamedQuery(name = "StarGui.findById", query = "SELECT s FROM StarGui s WHERE s.id = :id")})
public class StarGui implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Short id;
    @Basic(optional = false)
    @Lob
    @Column(name = "Image")
    private byte[] image;

    public StarGui() {
    }

    public StarGui(Short id) {
        this.id = id;
    }

    public StarGui(Short id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
        if (!(object instanceof StarGui)) {
            return false;
        }
        StarGui other = (StarGui) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xorgame.StarGui[ id=" + id + " ]";
    }
    
}

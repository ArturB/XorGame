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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class for gui table - contains GUI images that should be displayed by the game client. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "Gui")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gui.findAll", query = "SELECT g FROM Gui g"),
    @NamedQuery(name = "Gui.findById", query = "SELECT g FROM Gui g WHERE g.id = :id"),
    @NamedQuery(name = "Gui.findByLanguage", query = "SELECT g FROM Gui g WHERE g.language = :language")})
public class Gui implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;
    @Basic(optional = false)
    @Lob
    @Column(name = "Image")
    private byte[] image;
    @Basic(optional = false)
    @Column(name = "Language")
    private String language;

    public Gui() {
    }

    public Gui(String id) {
        this.id = id;
    }

    public Gui(String id, byte[] image, String language) {
        this.id = id;
        this.image = image;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
        if (!(object instanceof Gui)) {
            return false;
        }
        Gui other = (Gui) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xorgame.Gui[ id=" + id + " ]";
    }
    
}

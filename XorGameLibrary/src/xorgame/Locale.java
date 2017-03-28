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
 * Entity class for locale table - contains all game client text in different languages. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "Locale")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locale.findAll", query = "SELECT l FROM Locale l"),
    @NamedQuery(name = "Locale.findById", query = "SELECT l FROM Locale l WHERE l.id = :id"),
    @NamedQuery(name = "Locale.findByLanguage", query = "SELECT l FROM Locale l WHERE l.language = :language")})
public class Locale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private String id;
    @Basic(optional = false)
    @Lob
    @Column(name = "Text")
    private String text;
    @Basic(optional = false)
    @Column(name = "Language")
    private String language;

    public Locale() {
    }

    public Locale(String id) {
        this.id = id;
    }

    public Locale(String id, String text, String language) {
        this.id = id;
        this.text = text;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        if (!(object instanceof Locale)) {
            return false;
        }
        Locale other = (Locale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xorgame.Locale[ id=" + id + " ]";
    }
    
}

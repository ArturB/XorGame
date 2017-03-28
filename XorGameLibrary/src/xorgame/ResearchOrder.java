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
 * Entity class for research_order table - containing information about currently researched technologies. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "Research_Order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResearchOrder.findAll", query = "SELECT r FROM ResearchOrder r"),
    @NamedQuery(name = "ResearchOrder.findById", query = "SELECT r FROM ResearchOrder r WHERE r.id = :id"),
    @NamedQuery(name = "ResearchOrder.findByItem", query = "SELECT r FROM ResearchOrder r WHERE r.item = :item"),
    @NamedQuery(name = "ResearchOrder.findByAmount", query = "SELECT r FROM ResearchOrder r WHERE r.amount = :amount"),
    @NamedQuery(name = "ResearchOrder.findByStart", query = "SELECT r FROM ResearchOrder r WHERE r.start = :start"),
    @NamedQuery(name = "ResearchOrder.findByEnd", query = "SELECT r FROM ResearchOrder r WHERE r.end = :end")})
public class ResearchOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Item")
    private String item;
    @Basic(optional = false)
    @Column(name = "Amount")
    private short amount;
    @Basic(optional = false)
    @Column(name = "Start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Basic(optional = false)
    @Column(name = "End")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;
    @JoinColumns({
        @JoinColumn(name = "X", referencedColumnName = "X"),
        @JoinColumn(name = "Y", referencedColumnName = "Y"),
        @JoinColumn(name = "Position", referencedColumnName = "Position")})
    @ManyToOne(optional = false)
    private Planet planet;

    public ResearchOrder() {
    }

    public ResearchOrder(Integer id) {
        this.id = id;
    }

    public ResearchOrder(Integer id, String item, short amount, Date start, Date end) {
        this.id = id;
        this.item = item;
        this.amount = amount;
        this.start = start;
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public short getAmount() {
        return amount;
    }

    public void setAmount(short amount) {
        this.amount = amount;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResearchOrder)) {
            return false;
        }
        ResearchOrder other = (ResearchOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        Format formatter = new SimpleDateFormat("dd.MM 'at' hh:mm:ss");
        return getItem() + ", lvl " + getAmount() + ", end on " + formatter.format(getEnd()); 
    }
    
}

/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

import java.io.Serializable;
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
import java.text.*;

/**
 * Database entity class for building_order table - containing information about buildings currently built on the planets. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "Building_Order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BuildingOrder.findAll", query = "SELECT b FROM BuildingOrder b"),
    @NamedQuery(name = "BuildingOrder.findById", query = "SELECT b FROM BuildingOrder b WHERE b.id = :id"),
    @NamedQuery(name = "BuildingOrder.findByLocation", query = "SELECT b FROM BuildingOrder b WHERE b.location = :location"),
    @NamedQuery(name = "BuildingOrder.findByItem", query = "SELECT b FROM BuildingOrder b WHERE b.item = :item"),
    @NamedQuery(name = "BuildingOrder.findByAmount", query = "SELECT b FROM BuildingOrder b WHERE b.amount = :amount"),
    @NamedQuery(name = "BuildingOrder.findByStart", query = "SELECT b FROM BuildingOrder b WHERE b.start = :start"),
    @NamedQuery(name = "BuildingOrder.findByEnd", query = "SELECT b FROM BuildingOrder b WHERE b.end = :end")})
public class BuildingOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Location")
    private String location;
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

    public BuildingOrder() {
    }

    public BuildingOrder(Integer id) {
        this.id = id;
    }

    public BuildingOrder(Integer id, String location, String item, short amount, Date start, Date end) {
        this.id = id;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        if (!(object instanceof BuildingOrder)) {
            return false;
        }
        BuildingOrder other = (BuildingOrder) object;
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

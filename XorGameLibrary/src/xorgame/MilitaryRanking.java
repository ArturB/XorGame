/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class for military_ranking view - contains all users ordered descending by their military points. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "military_ranking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MilitaryRanking.findAll", query = "SELECT m FROM MilitaryRanking m"),
    @NamedQuery(name = "MilitaryRanking.findByNick", query = "SELECT m FROM MilitaryRanking m WHERE m.nick = :nick"),
    @NamedQuery(name = "MilitaryRanking.findByMilitaryPoints", query = "SELECT m FROM MilitaryRanking m WHERE m.militaryPoints = :militaryPoints")})
public class MilitaryRanking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Nick")
    private String nick;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MilitaryPoints")
    private Double militaryPoints;

    public MilitaryRanking() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Double getMilitaryPoints() {
        return militaryPoints;
    }

    public void setMilitaryPoints(Double militaryPoints) {
        this.militaryPoints = militaryPoints;
    }
    
}

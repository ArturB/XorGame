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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class for total_ranking view - containing all users ordered descending by their total points. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "total_ranking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TotalRanking.findAll", query = "SELECT t FROM TotalRanking t"),
    @NamedQuery(name = "TotalRanking.findByNick", query = "SELECT t FROM TotalRanking t WHERE t.nick = :nick"),
    @NamedQuery(name = "TotalRanking.findByTotalPoints", query = "SELECT t FROM TotalRanking t WHERE t.totalPoints = :totalPoints")})
public class TotalRanking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Nick")
    private String nick;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TotalPoints")
    private Double totalPoints;

    public TotalRanking() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Double totalPoints) {
        this.totalPoints = totalPoints;
    }
    
}
